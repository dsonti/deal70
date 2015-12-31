package com.sras.client.action;

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
import com.sras.client.utils.Utilities;
import com.sras.dao.BaseDao;
import com.sras.dao.DealDao;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealData;
import com.sras.datamodel.DealViewData;

public class MainCommand extends Command {
	private static String TEMPLATE_NAME = "index.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	public MainCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		Long storeId = Utilities.getLongFromRequest(request, "st");
		Long categoryId = Utilities.getLongFromRequest(request, "ct");
		String location = request.getParameter("lc");
		try {
			DealData deal = new DealData();
			if (storeId != null && storeId.longValue() > 0) {
				deal.setStoreId(storeId);
			}
			if (categoryId != null && categoryId.longValue() > 0) {
				deal.setCategoryId(categoryId);
			}
			if (location != null && location.trim().length() > 0) {
				deal.setLocation(location);
			}
			DealDao dao = new DealDao(deal);
			ArrayList<DataModel> deals = new ArrayList<DataModel>();// dao.enumerate();
			String jsonStr = "No deals available!";
			if (deals != null && deals.size() > 0) {
				Gson gson = new GsonBuilder().create();
				jsonStr = gson.toJson(deals, ArrayList.class);
			}
			jsonStr = getDeals();
			ctx.put("ajax_response_data", jsonStr);
		} catch (Exception e) {
			ctx.put("ajax_response_data", "Failed while retrieving deals!!");
		}
		return "ajax_template.vm";
	}

	public String doGet() throws Exception {
		String hostName = Utilities.getRemoteHostName(request);
		ctx.put("hostName", hostName);
		// MailUtils.sendMail("itsras@gmail.com", "Test Mail", "Test Mail");
		return TEMPLATE_NAME;
	}

	public String doAjaxPost() throws Exception {
		return "ajax_template.vm";
	}

	private String getDeals() throws Exception {
		String sql = "SELECT ID,STORE_ID,CATEGORY_ID,LOCATION,TITLE,DESCRIPTION1,DESCRIPTION2,"
				+ "DEAL_CODE,STEPS_TO_CONSUME,VIEW_COUNT,IS_ACTIVE,DEAL_IMG_NAME,CREATE_DATE,EXPIRY_DATE,UPDATE_DATE,CREATED_BY,PRICE,DISCOUNT,DISCOUNTED_PRICE,"
				+ "STORE_NAME,STORE_IMG_NAME,STORE_URL,CATEGORY_NAME,PARENT_ID FROM DEAL_DATA_VW";
		Connection connection = BaseDao.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		DealViewData dealView = new DealViewData();
		ArrayList<DealViewData> dealsView = new ArrayList<DealViewData>();
		while (rs.next()) {
			dealView = new DealViewData();
			dealView.setId(rs.getLong("ID"));
			dealView.setCategoryId(rs.getLong("CATEGORY_ID"));
			dealView.setStoreId(rs.getLong("STORE_ID"));
			dealView.setLocation(rs.getString("LOCATION"));
			dealView.setTitle(rs.getString("TITLE"));
			dealView.setDescription1(rs.getString("DESCRIPTION1"));
			dealView.setDescription2(rs.getString("DESCRIPTION2"));
			dealView.setCode(rs.getString("DEAL_CODE"));
			dealView.setStepsToConsume(rs.getString("STEPS_TO_CONSUME"));
			dealView.setViewCount(rs.getLong("VIEW_COUNT"));
			dealView.setIsActive(rs.getBoolean("IS_ACTIVE"));
			dealView.setDealImage(rs.getString("DEAL_IMG_NAME"));
			dealView.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
			dealView.setCreateDate(rs.getTimestamp("CREATE_DATE"));
			dealView.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
			dealView.setCreatedBy(rs.getString("CREATED_BY"));
			dealView.setPrice(rs.getString("PRICE"));
			dealView.setDiscount(rs.getString("DISCOUNT"));
			dealView.setDiscountedPrice(rs.getString("DISCOUNTED_PRICE"));
			dealView.setCategoryName(rs.getString("CATEGORY_NAME"));
			dealView.setParentCategoryId(rs.getLong("PARENT_ID"));
			dealView.setStoreName(rs.getString("STORE_NAME"));
			dealView.setImageName(rs.getString("STORE_IMG_NAME"));
			dealView.setUrl(rs.getString("STORE_URL"));
			dealsView.add(dealView);
		}
		Gson gson = new GsonBuilder().create();

		String jsonStr = gson.toJson(dealsView, ArrayList.class);
		return jsonStr;
	}

}
