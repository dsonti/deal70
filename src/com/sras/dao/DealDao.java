package com.sras.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.FixedValueException;
import com.sras.datamodel.exceptions.TMException;

public class DealDao extends BaseDao {

	private static Logger logger = Logger.getLogger(DealDao.class.getName());

	public DealDao(DataModel datamodel) {
		super(datamodel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long create() throws DataModelException, TMException, SQLException {
		DealData deal = (DealData) datamodel;

		ResultSet rst = null;
		try {
			String sql = CREATE_DEAL;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();

			bindVars.add(SQLValue.Long(deal.getStoreId()));
			bindVars.add(SQLValue.Long(deal.getCategoryId()));
			bindVars.add(SQLValue.String(deal.getLocation()));
			bindVars.add(SQLValue.String(deal.getTitle()));
			bindVars.add(SQLValue.String(deal.getDescription1()));
			bindVars.add(SQLValue.String(deal.getDescription2()));
			bindVars.add(SQLValue.String(deal.getCode()));
			bindVars.add(SQLValue.String(deal.getStepsToConsume()));
			bindVars.add(SQLValue.Long(deal.getViewCount()));
			bindVars.add(SQLValue.Boolean(deal.getIsActive()));
			bindVars.add(SQLValue.String(deal.getDealImage()));
			bindVars.add(SQLValue.Timestamp(deal.getCreateDate()));
			bindVars.add(SQLValue.Timestamp(deal.getExpiryDate()));
			bindVars.add(SQLValue.Timestamp(deal.getUpdateDate()));
			bindVars.add(SQLValue.String(deal.getCreatedBy()));

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
		DealData deal = (DealData) datamodel;

		ResultSet rst = null;
		try {
			String sql = UPDATE_DEAL;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();

			bindVars.add(SQLValue.Long(deal.getStoreId()));
			bindVars.add(SQLValue.Long(deal.getCategoryId()));
			bindVars.add(SQLValue.String(deal.getLocation()));
			bindVars.add(SQLValue.String(deal.getTitle()));
			bindVars.add(SQLValue.String(deal.getDescription1()));
			bindVars.add(SQLValue.String(deal.getDescription2()));
			bindVars.add(SQLValue.String(deal.getCode()));
			bindVars.add(SQLValue.String(deal.getStepsToConsume()));
			bindVars.add(SQLValue.Long(deal.getViewCount()));
			bindVars.add(SQLValue.Boolean(deal.getIsActive()));
			bindVars.add(SQLValue.String(deal.getDealImage()));
			bindVars.add(SQLValue.Timestamp(deal.getCreateDate()));
			bindVars.add(SQLValue.Timestamp(deal.getExpiryDate()));
			bindVars.add(SQLValue.Timestamp(deal.getUpdateDate()));
			bindVars.add(SQLValue.String(deal.getCreatedBy()));
			bindVars.add(SQLValue.Long(deal.getId()));

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
		DealData deal = (DealData) datamodel;

		ResultSet rst = null;
		try {
			String sql = READ_DEAL;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (deal.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getId()));
			}
			if (deal.getStoreId() > 0) {
				sql += AND + "`STORE_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getStoreId()));
			}
			if (deal.getCategoryId() > 0) {
				sql += AND + "`CATEGORY_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getCategoryId()));
			}
			if (deal.getSubCategoryIds() != null) {
				sql += AND + "`CATEGORY_ID` in (?) ";
				bindVars.add(SQLValue.String(deal.getSubCategoryIds()));
			}
			if (deal.getLocation() != null) {
				sql += AND + "`LOCATION` = ? ";
				bindVars.add(SQLValue.String(deal.getLocation()));
			}
			if (deal.getTitle() != null) {
				sql += AND + "`NAME` like ? ";
				bindVars.add(SQLValue.String(deal.getTitle()));
			}

			logger.debug("QUERY - Loading Store :" + sql);
			rst = executeQuery(sql, bindVars);
			ArrayList<DataModel> deals = new ArrayList<DataModel>();
			while (!rst.next()) {
				deals.add(loadDealVO(null, rst));
			}
			return deals;
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public int delete() throws DataModelException, TMException, SQLException {
		DealData deal = (DealData) datamodel;

		ResultSet rst = null;
		try {
			String sql = DELETE_DEAL;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (deal.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getId()));
			}
			if (deal.getStoreId() > 0) {
				sql += AND + "`STORE_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getStoreId()));
			}
			if (deal.getCategoryId() > 0) {
				sql += AND + "`CATEGORY_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getCategoryId()));
			}
			if (deal.getSubCategoryIds() != null) {
				sql += AND + "`CATEGORY_ID` in (?) ";
				bindVars.add(SQLValue.String(deal.getSubCategoryIds()));
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
		DealData deal = (DealData) datamodel;

		ResultSet rst = null;
		try {
			String sql = READ_DEAL;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (deal.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getId()));
			}
			if (deal.getStoreId() > 0) {
				sql += AND + "`STORE_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getStoreId()));
			}
			if (deal.getCategoryId() > 0) {
				sql += AND + "`CATEGORY_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getCategoryId()));
			}
			if (deal.getSubCategoryIds() != null) {
				sql += AND + "`CATEGORY_ID` in (?) ";
				bindVars.add(SQLValue.String(deal.getSubCategoryIds()));
			}
			if (deal.getTitle() != null) {
				sql += AND + "`NAME` like ? ";
				bindVars.add(SQLValue.String(deal.getTitle()));
			}

			logger.debug("QUERY - Loading Deals :" + sql);
			rst = executeQuery(sql, bindVars);

			return loadDealVO(deal, rst);
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
	public DataModel loadDealVO(DealData deal, ResultSet rst)
			throws TMException, SQLException {
		if (!rst.next()) {
			return null;
		}

		if (deal == null) {
			deal = new DealData();
		}
		try {
			deal.setId(rst.getLong(ID));
		} catch (FixedValueException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}

		deal.setStoreId(rst.getLong(STORE_ID));
		deal.setCategoryId(rst.getLong(CATEGORY_ID));
		deal.setLocation(rst.getString(LOCATION));
		deal.setTitle(rst.getString(TITLE));
		deal.setDescription1(rst.getString(DESCRIPTION1));
		deal.setDescription2(rst.getString(DESCRIPTION2));
		deal.setCode(rst.getString(DEAL_CODE));
		deal.setStepsToConsume(rst.getString(STEPS_TO_CONSUME));
		deal.setViewCount(rst.getLong(VIEW_COUNT));
		deal.setIsActive(rst.getBoolean(IS_ACTIVE));
		deal.setDealImage(rst.getString(IMG_NAME));
		deal.setCreateDate(rst.getDate(CREATE_DATE));
		deal.setExpiryDate(rst.getDate(EXPIRY_DATE));
		deal.setUpdateDate(rst.getDate(UPDATE_DATE));
		deal.setCreatedBy(rst.getString(CREATED_BY));
		return deal;
	}

	public static final String READ_DEAL = "SELECT * FROM `DEAL_DATA` WHERE 1=1 ";
	public static final String DELETE_DEAL = "DELETE FROM `DEAL_DATA` WHERE 1=1 ";
	public static final String UPDATE_DEAL = "UPDATE `DEAL_DATA` SET `STORE_ID` = ? ,`CATEGORY_ID` = ?, "
			+ "`LOCATION` = ? ,`TITLE` = ? ,`DESCRIPTION1` = ? ,`DESCRIPTION2` = ? ,`DEAL_CODE` = ? ,`STEPS_TO_CONSUME` = ?, `VIEW_COUNT` = ?, `IS_ACTIVE` = ?, "
			+ "`IMG_NAME` = ?, `CREATE_DATE` = ?, `EXPIRY_DATE` = ?, `UPDATE_DATE` = ?, `CREATED_BY` = ? WHERE `ID` = ?";
	public static final String CREATE_DEAL = "INSERT INTO `DEAL_DATA` (`STORE_ID`,`CATEGORY_ID`,`LOCATION`,`TITLE`,`DESCRIPTION1`,`DESCRIPTION2`,"
			+ "`DEAL_CODE`,`STEPS_TO_CONSUME`,`VIEW_COUNT`,`IS_ACTIVE`,`IMG_NAME`,`CREATE_DATE`,`EXPIRY_DATE`,`UPDATE_DATE`,`CREATED_BY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String STORE_ID = "STORE_ID";
	public static final String CATEGORY_ID = "CATEGORY_ID";
	public static final String LOCATION = "LOCATION";
	public static final String TITLE = "TITLE";
	public static final String DESCRIPTION1 = "DESCRIPTION1";
	public static final String DESCRIPTION2 = "DESCRIPTION2";
	public static final String DEAL_CODE = "DEAL_CODE";
	public static final String STEPS_TO_CONSUME = "STEPS_TO_CONSUME";
	public static final String VIEW_COUNT = "VIEW_COUNT";
	public static final String IS_ACTIVE = "IS_ACTIVE";
	public static final String IMG_NAME = "IMG_NAME";
	public static final String CREATE_DATE = "CREATE_DATE";
	public static final String EXPIRY_DATE = "EXPIRY_DATE";
	public static final String UPDATE_DATE = "UPDATE_DATE";
	public static final String CREATED_BY = "CREATED_BY";
}
