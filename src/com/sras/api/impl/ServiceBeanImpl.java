package com.sras.api.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import com.sras.api.ServiceBean;
import com.sras.dao.BaseDao;
import com.sras.dao.CategoryDao;
import com.sras.dao.DealViewDao;
import com.sras.dao.StoreDao;
import com.sras.datamodel.CategoryData;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealViewData;
import com.sras.datamodel.StoreData;

public class ServiceBeanImpl implements ServiceBean {

	private static ServiceBeanImpl serviceBean = null;

	public static synchronized ServiceBeanImpl getInstance() {
		if (serviceBean == null) {
			serviceBean = new ServiceBeanImpl();
		}
		return serviceBean;
	}

	@Override
	public ArrayList<DataModel> getAllDeal() throws Exception {
		DealViewData deal = new DealViewData();
		DealViewDao dao = new DealViewDao(deal);
		return dao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getAllDeals(long storeId, String storeName)
			throws Exception {
		DealViewData deal = new DealViewData();
		if (storeId > 0) {
			deal.setStoreId(storeId);
		}
		if (storeName != null) {
			deal.setStoreName(storeName);
		}
		DealViewDao dao = new DealViewDao(deal);
		return dao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getAllDeals(long categoryId,
			String categoryName, boolean isParent) throws Exception {
		DealViewData deal = new DealViewData();
		if (categoryId > 0) {
			if (isParent) {
				deal.setParentCategoryId(categoryId);
			} else {
				deal.setCategoryId(categoryId);
			}
		}
		if (categoryName != null) {
			deal.setCategoryName(categoryName);
		}
		DealViewDao dao = new DealViewDao(deal);
		return dao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getAllDeals(String location) throws Exception {
		DealViewData deal = new DealViewData();
		if (location != null) {
			deal.setLocation(location);
		}
		DealViewDao dao = new DealViewDao(deal);
		return dao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getPopularDeals(long storeId, long categoryId,
			String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataModel> getHotDeals(long storeId, long categoryId,
			String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataModel getDeal(long dealId) throws Exception {
		DealViewData deal = new DealViewData();
		deal.setId(dealId);
		DealViewDao dao = new DealViewDao(deal);
		return dao.read();
	}

	@Override
	public DataModel getStoreDetails(long id) throws Exception {
		StoreData stData = new StoreData();
		stData.setId(id);
		StoreDao stDao = new StoreDao(stData);
		return stDao.read();
	}

	@Override
	public ArrayList<DataModel> getAllStores() throws Exception {
		StoreData stData = new StoreData();
		StoreDao stDao = new StoreDao(stData);
		return stDao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getAllStores(String searchStr) throws Exception {
		StoreData stData = new StoreData();
		stData.setSearchStr(searchStr);
		StoreDao stDao = new StoreDao(stData);
		return stDao.enumerate();
	}

	@Override
	public DataModel getStoreDetails(String storeName) throws Exception {
		StoreData stData = new StoreData();
		stData.setName(storeName);
		StoreDao stDao = new StoreDao(stData);
		return stDao.read();
	}

	@Override
	public ArrayList<DataModel> getFavoriteStores(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataModel> getPopularStores() throws Exception {
		StoreData stData = new StoreData();
		stData.setIsPopular(true);
		StoreDao stDao = new StoreDao(stData);
		return stDao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getNearByStores(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataModel getCategoryDetails(long id) throws Exception {
		CategoryData catData = new CategoryData();
		catData.setId(id);
		CategoryDao catDao = new CategoryDao(catData);
		return catDao.read();
	}

	@Override
	public ArrayList<DataModel> getAllCategories() throws Exception {
		CategoryData catData = new CategoryData();
		CategoryDao catDao = new CategoryDao(catData);
		return catDao.enumerate();
	}

	@Override
	public ArrayList<DataModel> getFavoriteCategories(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataModel> getPopularCategories() throws Exception {
		CategoryData catData = new CategoryData();
		catData.setPopular(true);
		CategoryDao catDao = new CategoryDao(catData);
		return catDao.enumerate();
	}

	@Override
	public DataModel getLoction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataModel> getAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DataModel> getFavoriteLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveAsFavorite(long userid, long storeId, long categoryId,
			String location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeFromFavorite(long userid, long storeId, long categoryId,
			String location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveDeal(DataModel dealData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveCategory(DataModel categoryData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveStore(DataModel storeData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveLocation(DataModel locationData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setViewCount(DataModel data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataModel getUser(DataModel userData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createUser(DataModel userData) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Hashtable<String, Long> getStoreStats(long storeId) throws Exception {
		Hashtable<String, Long> statsMap = new Hashtable<String, Long>();

		PreparedStatement ps = null;
		ResultSet rst = null;
		try {
			Connection con = BaseDao.getConnection();
			ps = con.prepareStatement("SELECT DEAL_TYPE, COUNT(*) FROM DEAL_DATA_VW WHERE IS_ACTIVE = ? AND STORE_ID = ? GROUP BY DEAL_TYPE");
			ps.setBoolean(1, true);
			ps.setLong(2, storeId);
			rst = ps.executeQuery();
			long totalCount = 0;
			while (rst.next()) {
				String codeType = rst.getString(1);
				Long count = rst.getLong(2);
				count = (count == null) ? new Long(0) : count;
				statsMap.put(codeType, count.longValue());
				totalCount += count;
			}
			statsMap.put("totalCount", totalCount);
		} finally {
			BaseDao.close(ps, rst);
		}
		return statsMap;
	}

	@Override
	public Hashtable<String, Long> getCategoryStats(long categoryId,
			boolean isParent) throws Exception {
		Hashtable<String, Long> statsMap = new Hashtable<String, Long>();

		String sql = "SELECT DEAL_TYPE, COUNT(*) FROM DEAL_DATA_VW WHERE IS_ACTIVE = ? ";

		if (isParent) {
			sql += " AND PARENT_ID = ? ";
		} else {
			sql += " AND CATEGORY_ID = ? ";
		}
		PreparedStatement ps = null;
		ResultSet rst = null;
		try {
			Connection con = BaseDao.getConnection();
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setLong(2, categoryId);
			rst = ps.executeQuery();
			long totalCount = 0;
			while (rst.next()) {
				String codeType = rst.getString(1);
				Long count = rst.getLong(2);
				if (codeType != null) {
					count = (count == null) ? new Long(0) : count;
					statsMap.put(codeType, count);
					totalCount += count;
				}
			}
			statsMap.put("totalCount", totalCount);
		} finally {
			BaseDao.close(ps, rst);
		}
		return statsMap;
	}

	@Override
	public Hashtable<String, Long> getLocationStats(String name)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
