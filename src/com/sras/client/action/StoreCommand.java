package com.sras.client.action;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sras.dao.BaseDao;
import com.sras.dao.DealViewDao;
import com.sras.dao.StoreDao;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealViewData;
import com.sras.datamodel.StoreData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class StoreCommand extends Command {

	private String TEMPLATE_NAME = "store.vm";
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
		try {
			String storeName = request.getParameter("name");
			DealViewData deal = new DealViewData();
			deal.setStoreName(storeName);
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
		String requestURI = (String) ctx.get("requestURI");
		requestURI = URLDecoder.decode(requestURI, "UTF-8");
		int i = requestURI.indexOf('/');
		String storeName = null;
		if (i > 0 && requestURI.indexOf("store") == 0) {
			storeName = requestURI.substring(i + 1);
			StoreData stData = new StoreData();
			stData.setName(storeName);
			StoreDao stDao = new StoreDao(stData);
			stData = (StoreData) stDao.read();
			if (stData != null) {
				ctx.put("storeData", stData);
				getStoreStats(stData.getId());
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

	public void getStoreStats(long storeId) throws Exception {
		Connection con = BaseDao.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT DEAL_TYPE, COUNT(*) FROM DEAL_DATA_VW WHERE IS_ACTIVE = ? AND STORE_ID = ? GROUP BY DEAL_TYPE");
		ps.setBoolean(1, true);
		ps.setLong(2, storeId);
		ResultSet rst = ps.executeQuery();
		long totalCount = 0;
		while (rst.next()) {
			String codeType = rst.getString(1);
			Long count = rst.getLong(2);
			count = (count == null) ? new Long(0) : count;
			ctx.put(codeType, count.longValue());
			totalCount += count;
		}
		ctx.put("totalCount", totalCount);
	}
}
