package com.sras.client.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.sras.client.utils.Utilities;
import com.sras.dao.DealDao;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealData;

public class DealCommand extends Command {
	private static String TEMPLATE_NAME = "index.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	public DealCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		long storeId = Utilities.getLongFromRequest(request, "st");
		long categoryId = Utilities.getLongFromRequest(request, "ct");
		String location = request.getParameter("lc");
		try {
			DealData deal = new DealData();
			if (storeId > 0) {
				deal.setStoreId(storeId);
			}
			if (categoryId > 0) {
				deal.setCategoryId(categoryId);
			}
			if (location != null && location.trim().length() > 0) {
				deal.setLocation(location);
			}
			DealDao dao = new DealDao(deal);
			ArrayList<DataModel> deals = dao.enumerate();
			ctx.put("ajax_response_data", "Ajax call successful!");
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
}
