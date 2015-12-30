package com.sras.client.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.sras.client.utils.Utilities;
import com.sras.dao.DealDao;
import com.sras.dao.StoreDao;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealData;
import com.sras.datamodel.StoreData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class StoreCommand extends Command {

	private static String TEMPLATE_NAME = "stores.vm";
	protected static Category log = Category.getInstance(MainCommand.class);
	static Hashtable<Long, StoreData> storesMap = new Hashtable<Long, StoreData>();

	static {
		loadStores();
	}

	public static void loadStores() {
		try {
			StoreData stData = new StoreData();
			StoreDao stDao = new StoreDao(stData);
			ArrayList<DataModel> stores = stDao.enumerate();
			for (DataModel std : stores) {
				StoreData data = (StoreData) std;
				storesMap.put(data.getId(), data);
			}
		} catch (DataModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		long storeId = Utilities.getLongFromRequest(request, "st");
		try {
			DealData deal = new DealData();
			if (storeId > 0) {
				deal.setStoreId(storeId);
			}
			DealDao dao = new DealDao(deal);
			ArrayList deals = dao.enumerate();
			ctx.put("ajax_response_data", "Ajax call successful!");
		} catch (Exception e) {
			ctx.put("ajax_response_data", "Failed while retrieving deals!!");

		}
		return "ajax_template.vm";
	}

	public String doGet() throws Exception {
		long storeId = Utilities.getLongFromRequest(request, "st");
		ctx.put("storeData", storesMap.get(storeId));
		return TEMPLATE_NAME;
	}

	public String doAjaxPost() throws Exception {
		return "ajax_template.vm";
	}
}
