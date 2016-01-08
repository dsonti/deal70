package com.sras.api;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sras.datamodel.DataModel;

public interface ServiceBean {

	/*
	 * Fetches all deals
	 */
	public ArrayList<DataModel> getAllDeal() throws Exception;

	public ArrayList<DataModel> getAllDeals(long storeId, String storeName)
			throws Exception;

	public ArrayList<DataModel> getAllDeals(long categoryId,
			String categoryName, boolean isParent) throws Exception;

	public ArrayList<DataModel> getAllDeals(String location) throws Exception;

	public ArrayList<DataModel> getPopularDeals(long storeId, long categoryId,
			String location);

	public ArrayList<DataModel> getHotDeals(long storeId, long categoryId,
			String location);

	public DataModel getDeal(long dealId) throws Exception;

	public DataModel getStoreDetails(long id) throws Exception;

	public DataModel getStoreDetails(String storeName) throws Exception;

	public ArrayList<DataModel> getAllStores() throws Exception;

	public ArrayList<DataModel> getAllStores(String searchStr) throws Exception;
	
	public ArrayList<DataModel> getFavoriteStores(long userId);

	public ArrayList<DataModel> getPopularStores() throws Exception;

	public Hashtable<String, Long> getStoreStats(long storeId) throws Exception;

	public ArrayList<DataModel> getNearByStores(long userId);

	public DataModel getCategoryDetails(long id) throws Exception;

	public ArrayList<DataModel> getAllCategories() throws Exception;

	public ArrayList<DataModel> getFavoriteCategories(long userId);

	public ArrayList<DataModel> getPopularCategories() throws Exception;

	public Hashtable<String, Long> getCategoryStats(long categoryId,
			boolean isParent) throws Exception;

	public DataModel getLoction(String name);

	public ArrayList<DataModel> getAllLocations();

	public ArrayList<DataModel> getFavoriteLocations();

	public Hashtable<String, Long> getLocationStats(String name)
			throws Exception;

	public int saveAsFavorite(long userid, long storeId, long categoryId,
			String location);

	public int removeFromFavorite(long userid, long storeId, long categoryId,
			String location);

	public int saveDeal(DataModel dealData);

	public int saveCategory(DataModel categoryData);

	public int saveStore(DataModel storeData);

	public int saveLocation(DataModel locationData);

	public int setViewCount(DataModel data);

	public DataModel getUser(DataModel userData);

	public int createUser(DataModel userData);

}
