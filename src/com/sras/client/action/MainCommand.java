package com.sras.client.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sras.client.utils.Utilities;
import com.sras.datamodel.DataModel;

public class MainCommand extends Command {
	private static String TEMPLATE_NAME = "index.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	public MainCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		try {
			ArrayList<DataModel> deals = serviceBean.getAllDeal();
			String jsonStr = "No deals available!";
			if (deals != null && deals.size() > 0) {
				Gson gson = new GsonBuilder().create();
				jsonStr = gson.toJson(deals, ArrayList.class);
			}
			ctx.put("ajax_response_data", jsonStr);
		} catch (Exception e) {
			Writer result = new StringWriter();
			PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			ctx.put("ajax_response_data",
					"Failed while retrieving deals!!" + e.getLocalizedMessage()
							+ " :: " + result.toString());
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
		// To save store & category as favorites
		String type = request.getParameter("type");
		String dealId = request.getParameter("id");
		String couponId = request.getParameter("cid");
		String storeId = request.getParameter("stid");
		String action = request.getParameter("action");
		if (action != null && action.equalsIgnoreCase("map")) {
			// TODO: map to user
		} else {
			// unmap to user
		}

		// To handle store search in site header
		String storeName = request.getParameter("search");
		String jsonStr = "[]";
		if (storeName != null && storeName.trim().length() > 0) {
			ArrayList<DataModel> stores = serviceBean.getAllStores(storeName);
			Gson gson = new GsonBuilder().create();
			jsonStr = gson.toJson(stores, ArrayList.class);
		}

		// To handle facebook login & logout...
		String ltype = request.getParameter("ltype");
		if (ltype != null) {
			FacebookLoginCommand cmd = new FacebookLoginCommand(request,
					response, ctx);
			String template = cmd.doPost();
		}
		ctx.put("ajax_response_data", jsonStr);
		return "ajax_template.vm";
	}

	public String doPost() throws Exception {
		return TEMPLATE_NAME;
	}
}
