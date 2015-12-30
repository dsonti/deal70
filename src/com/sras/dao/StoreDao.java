package com.sras.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.sras.datamodel.DataModel;
import com.sras.datamodel.StoreData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.FixedValueException;
import com.sras.datamodel.exceptions.TMException;

public class StoreDao extends BaseDao {

	private static Logger logger = Logger.getLogger(StoreDao.class.getName());

	public StoreDao(DataModel datamodel) {
		super(datamodel);
	}

	@Override
	public long create() throws DataModelException, TMException, SQLException {
		StoreData store = (StoreData) datamodel;

		ResultSet rst = null;
		try {
			String sql = CREATE_STORE;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();

			bindVars.add(SQLValue.String(store.getName()));
			bindVars.add(SQLValue.String(store.getImageName()));
			bindVars.add(SQLValue.String(store.getDescritpion()));
			bindVars.add(SQLValue.String(store.getUrl()));
			bindVars.add(SQLValue.Boolean(store.getIsPopular()));
			bindVars.add(SQLValue.String(store.getLocation()));
			bindVars.add(SQLValue.Boolean(store.getIsOnline()));
			bindVars.add(SQLValue.Long(store.getViewCount()));
			bindVars.add(SQLValue.String(store.getGeoLocation()));
			bindVars.add(SQLValue.Timestamp(store.getCreateDate()));
			bindVars.add(SQLValue.Timestamp(store.getUpdateDate()));
			bindVars.add(SQLValue.String(store.getCreatedBy()));

			logger.debug("QUERY - Loading store :" + sql);
			return executeUpdate(sql, bindVars);
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public long update() throws DataModelException, TMException, SQLException {
		StoreData store = (StoreData) datamodel;

		ResultSet rst = null;
		try {
			String sql = UPDATE_STORE;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();

			bindVars.add(SQLValue.String(store.getName()));
			bindVars.add(SQLValue.String(store.getImageName()));
			bindVars.add(SQLValue.String(store.getDescritpion()));
			bindVars.add(SQLValue.String(store.getUrl()));
			bindVars.add(SQLValue.Boolean(store.getIsPopular()));
			bindVars.add(SQLValue.String(store.getLocation()));
			bindVars.add(SQLValue.Boolean(store.getIsOnline()));
			bindVars.add(SQLValue.Long(store.getViewCount()));
			bindVars.add(SQLValue.String(store.getGeoLocation()));
			bindVars.add(SQLValue.Timestamp(store.getCreateDate()));
			bindVars.add(SQLValue.Timestamp(store.getUpdateDate()));
			bindVars.add(SQLValue.String(store.getCreatedBy()));
			bindVars.add(SQLValue.Long(store.getId()));

			logger.debug("QUERY - Loading store :" + sql);
			return executeUpdate(sql, bindVars);
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public ArrayList<DataModel> enumerate() throws DataModelException,
			TMException, SQLException {
		StoreData store = (StoreData) datamodel;

		ResultSet rst = null;
		try {
			String sql = READ_STORE;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (store.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(store.getId()));
			}
			if (store.getName() != null) {
				sql += AND + "`NAME` = ? ";
				bindVars.add(SQLValue.String(store.getName()));
			}

			logger.debug("QUERY - Loading Store :" + sql);
			rst = executeQuery(sql, bindVars);
			ArrayList<DataModel> stores = new ArrayList<DataModel>();
			while (!rst.next()) {
				stores.add(loadStoreVO(null, rst));
			}
			return stores;
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public int delete() throws DataModelException, TMException, SQLException {
		StoreData store = (StoreData) datamodel;

		ResultSet rst = null;
		try {
			String sql = DELETE_STORE;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (store.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(store.getId()));
			}
			if (store.getName() != null) {
				sql += AND + "`NAME` = ? ";
				bindVars.add(SQLValue.String(store.getName()));
			}

			logger.debug("QUERY - Loading Store :" + sql);
			return executeUpdate(sql, bindVars);
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public DataModel read() throws DataModelException, TMException,
			SQLException {
		StoreData store = (StoreData) datamodel;

		ResultSet rst = null;
		try {
			String sql = READ_STORE;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (store.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(store.getId()));
			}
			if (store.getName() != null) {
				sql += AND + "`NAME` = ? ";
				bindVars.add(SQLValue.String(store.getName()));
			}

			logger.debug("QUERY - Loading Store :" + sql);
			rst = executeQuery(sql, bindVars);

			return loadStoreVO(store, rst);
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public boolean validateRules() throws TMException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @param store
	 * @param rst
	 * @throws SQLException
	 */
	public DataModel loadStoreVO(StoreData store, ResultSet rst)
			throws TMException, SQLException {
		if (!rst.next()) {
			return null;
		}

		if (store == null) {
			store = new StoreData();
		}
		try {
			store.setId(rst.getLong(ID));
		} catch (FixedValueException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		store.setName(rst.getString(NAME));
		store.setImageName(rst.getString(IMG_NAME));
		store.setDescritpion(rst.getString(DESCRIPTION));
		store.setUrl(rst.getString(STORE_URL));
		store.setIsPopular(rst.getBoolean(IS_POPULAR));
		store.setLocation(rst.getString(LOCATION));
		store.setIsOnline(rst.getBoolean(IS_ONLINE));
		store.setViewCount(rst.getLong(VIEW_COUNT));
		store.setGeoLocation(rst.getString(GEO_LOCATION));
		store.setCreateDate(rst.getDate(CREATE_DATE));
		store.setUpdateDate(rst.getDate(UPDATE_DATE));
		store.setCreatedBy(rst.getString(CREATED_BY));
		return store;
	}

	public static final String READ_STORE = "SELECT * FROM `STORE_DATA` WHERE 1=1 ";
	public static final String DELETE_STORE = "DELETE FROM `STORE_DATA` WHERE 1=1 ";
	public static final String UPDATE_STORE = "UPDATE `STORE_DATA` SET `NAME` = ? ,`IMG_NAME` = ?, "
			+ "`DESCRIPTION` = ? ,`STORE_URL` = ? ,`IS_POPULAR` = ? ,`LOCATION` = ? ,`IS_ONLINE` = ? ,`VIEW_COUNT` = ?, `GEO_LOCATION` = ?, `CREATE_DATE` = ?, "
			+ "`UPDATE_DATE` = ?, `CREATED_BY` = ? WHERE `ID` = ?";
	public static final String CREATE_STORE = "INSERT INTO `STORE_DATA` (`NAME`,`IMG_NAME`,`DESCRIPTION`,`STORE_URL`,`IS_POPULAR`,`LOCATION`,`IS_ONLINE`,`VIEW_COUNT`,`GEO_LOCATION`,`CREATE_DATE`,`UPDATE_DATE`,`CREATED_BY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String NAME = "NAME";
	public static final String IMG_NAME = "IMG_NAME";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String STORE_URL = "STORE_URL";
	public static final String IS_POPULAR = "IS_POPULAR";
	public static final String LOCATION = "LOCATION";
	public static final String IS_ONLINE = "IS_ONLINE";
	public static final String VIEW_COUNT = "VIEW_COUNT";
	public static final String GEO_LOCATION = "GEO_LOCATION";
	public static final String CREATE_DATE = "CREATE_DATE";
	public static final String UPDATE_DATE = "UPDATE_DATE";
	public static final String CREATED_BY = "CREATED_BY";

}
