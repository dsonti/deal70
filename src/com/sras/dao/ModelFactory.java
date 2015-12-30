package com.sras.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.sras.datamodel.AddressData;
import com.sras.datamodel.CategoryData;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealData;
import com.sras.datamodel.StoreData;
import com.sras.datamodel.UserData;
import com.sras.datamodel.UserSessionData;

public class ModelFactory {

	@SuppressWarnings("rawtypes")
	private static Map<Class, Class> modelToDao;

	static {
		if (modelToDao == null || modelToDao.size() == 0) {
			modelToDao = new HashMap<Class, Class>();
			modelToDao.put(UserData.class, UserDao.class);
			modelToDao.put(AddressData.class, AddressDao.class);
			modelToDao.put(UserSessionData.class, UserSessionDao.class);
			modelToDao.put(StoreData.class, StoreDao.class);
			modelToDao.put(DealData.class, DealDao.class);
			modelToDao.put(CategoryData.class, CategoryDao.class);
		}
	}

	public static BaseDao getImplementation(DataModel dataModel)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		if (dataModel == null) {
			return null;
		}
		Class modelClass = dataModel.getClass();
		Class daoClass = modelToDao.get(modelClass);
		Constructor<BaseDao> c = daoClass
				.getDeclaredConstructor(DataModel.class);
		return c.newInstance(dataModel);
	}
}
