package com.sras.api;

import java.util.ArrayList;

import com.sras.datamodel.DataModel;

public interface ServiceBean {

	/*
	 * Fetches all deals
	 */
	public ArrayList<DataModel> getAllDeal();

	public ArrayList<DataModel> getAllDeals(long storeId, long categoryId,
			String location);

	public ArrayList<DataModel> getPopularDeals(long storeId, long categoryId,
			String location);

	public ArrayList<DataModel> getHotDeals(long storeId, long categoryId,
			String location);

	public DataModel getDeal(long dealId);

	public DataModel getStoreDetails(long id);

	public ArrayList<DataModel> getAllStores();

	public ArrayList<DataModel> getAllStores(String storeName);

	public ArrayList<DataModel> getFavoriteStores(long userId);

	public ArrayList<DataModel> getPopularStores(long userId);

	public ArrayList<DataModel> getNearByStores(long userId);

	public DataModel getCategoryDetails(long id);

	public ArrayList<DataModel> getAllCategories();

	public ArrayList<DataModel> getFavoriteCategories(long userId);

	public ArrayList<DataModel> getPopularCategories(long userId);

	public DataModel getLoction(String name);

	public ArrayList<DataModel> getAllLocations();

	public ArrayList<DataModel> getFavoriteLocations();

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
