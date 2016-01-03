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
import com.sras.dao.DealViewDao;
import com.sras.dao.StoreDao;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealViewData;
import com.sras.datamodel.StoreData;

public class MainCommand extends Command {
	private static String TEMPLATE_NAME = "index.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	public MainCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		try {
			DealViewData deal = new DealViewData();
			DealViewDao dao = new DealViewDao(deal);
			ArrayList<DataModel> deals = dao.enumerate();
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
		String ltype = request.getParameter("ltype");
		String storeName = request.getParameter("search");
		String jsonStr = "[]";
		if (storeName != null && storeName.trim().length() > 0) {
			StoreData stData = new StoreData();
			stData.setSearchStr(storeName);
			StoreDao stDao = new StoreDao(stData);
			ArrayList<DataModel> stores = stDao.enumerate();
			Gson gson = new GsonBuilder().create();
			jsonStr = gson.toJson(stores, ArrayList.class);
		}
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
