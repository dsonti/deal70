package com.sras.client.action;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sras.dao.BaseDao;
import com.sras.dao.DealViewDao;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealViewData;

public class LocationCommand extends Command {
	private String TEMPLATE_NAME = "location.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	public LocationCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		try {
			String name = request.getParameter("name");
			DealViewData deal = new DealViewData();
			deal.setLocation(name);
			DealViewDao dao = new DealViewDao(deal);
			ArrayList<DataModel> deals = dao.enumerate();
			String jsonStr = "No deals available!";
			if (deals != null && deals.size() > 0) {
				Gson gson = new GsonBuilder().create();
				jsonStr = gson.toJson(deals, ArrayList.class);
			}
			ctx.put("ajax_response_data", jsonStr);
		} catch (Exception e) {
			ctx.put("ajax_response_data", "Failed while retrieving deals!!");
		}
		return "ajax_template.vm";
	}

	public String doGet() throws Exception {
		// To handle URL format like .../category/Accessories
		String requestURI = (String) ctx.get("requestURI");
		requestURI = URLDecoder.decode(requestURI, "UTF-8");
		String locations = "Bangalore, Chennai, Delhi, Mumbai, Pune, Hyderabad";
		int i = requestURI.indexOf('/');
		String locationName = null;
		if (i > 0 && requestURI.indexOf("location") == 0) {
			locationName = requestURI.substring(i + 1);
			if (locations.indexOf(locationName) >= 0) {
				ctx.put("locationName", locationName);
				getLocationStats(locationName);
				TEMPLATE_NAME = "location.vm";
			} else {
				TEMPLATE_NAME = "error.vm";
			}
		}
		// MailUtils.sendMail("itsras@gmail.com", "Test Mail", "Test Mail");
		return TEMPLATE_NAME;
	}

	public String doAjaxPost() throws Exception {
		return "ajax_template.vm";
	}

	public void getLocationStats(String location) throws Exception {
		PreparedStatement ps = null;
		ResultSet rst = null;
		try {
			Connection con = BaseDao.getConnection();
			ps = con.prepareStatement("SELECT DEAL_TYPE, COUNT(*) FROM DEAL_DATA_VW WHERE IS_ACTIVE = ? AND LOCATION = ? GROUP BY LOCATION");
			ps.setBoolean(1, true);
			ps.setString(2, location);
			rst = ps.executeQuery();
			long totalCount = 0;
			while (rst.next()) {
				String codeType = rst.getString(1);
				Long count = rst.getLong(2);
				count = (count == null) ? new Long(0) : count;
				ctx.put(codeType, count.longValue());
				totalCount += count;
			}
			ctx.put("totalCount", totalCount);
		} finally {
			BaseDao.close(ps, rst);
		}
	}
}
