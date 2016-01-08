package com.sras.client.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.StoreData;

public class StoreCommand extends Command {

	private String TEMPLATE_NAME = "store.vm";
	protected static Category log = Category.getInstance(MainCommand.class);
	static Hashtable<Long, StoreData> storesMap = new Hashtable<Long, StoreData>();

	static {
		loadStores();
	}

	public static void loadStores() {
		try {
			ArrayList<DataModel> stores = serviceBean.getAllStores();
			for (DataModel std : stores) {
				StoreData data = (StoreData) std;
				storesMap.put(data.getId(), data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Hashtable<Long, StoreData> getStores() {
		return storesMap;
	}

	public StoreCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		try {
			String storeName = request.getParameter("name");
			ArrayList<DataModel> deals = serviceBean.getAllDeals(-1, storeName);
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
		int i = requestURI.indexOf('/');
		String storeName = null;
		if (i > 0 && requestURI.indexOf("store") == 0) {
			storeName = requestURI.substring(i + 1);
			StoreData stData = (StoreData) serviceBean
					.getStoreDetails(storeName);
			if (stData != null) {
				ctx.put("storeData", stData);
				Hashtable<String, Long> statsMap = serviceBean
						.getStoreStats(stData.getId());
				Enumeration<String> keysEnum = statsMap.keys();
				while (keysEnum.hasMoreElements()) {
					String key = keysEnum.nextElement();
					ctx.put(key, statsMap.get(key));
				}
				TEMPLATE_NAME = "store.vm";
			} else {
				TEMPLATE_NAME = "error.vm";
			}

		}
		return TEMPLATE_NAME;
	}

	public String doAjaxPost() throws Exception {
		return "ajax_template.vm";
	}
}
