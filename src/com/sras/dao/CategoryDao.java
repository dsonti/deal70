package com.sras.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.sras.datamodel.CategoryData;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.FixedValueException;
import com.sras.datamodel.exceptions.TMException;

public class CategoryDao extends BaseDao {

	private static Logger logger = Logger
			.getLogger(CategoryDao.class.getName());

	public CategoryDao(DataModel datamodel) {
		super(datamodel);
	}

	@Override
	public long create() throws DataModelException, TMException, SQLException {
		CategoryData category = (CategoryData) datamodel;

		if (category == null)
			throw new TMException("INVALID_VO",
					"provided User instance is null");

		if (isEmptyOrNull(category.getName()))
			throw new TMException("INVALID_KEY", "required key not found");

		if (read() != null)
			throw new SQLException("USER_EXISTS",
					"Category Name already in use");

		ResultSet rst = null;
		try {
			String sql = CREATE_CATEGORY;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();

			bindVars.add(SQLValue.String(category.getName()));
			bindVars.add(SQLValue.Long(category.getParentId()));
			bindVars.add(SQLValue.String(category.getDescritpion()));
			bindVars.add(SQLValue.String(category.getImageName()));
			bindVars.add(SQLValue.Long(category.getViewCount()));
			bindVars.add(SQLValue.Timestamp(category.getCreateDate()));
			bindVars.add(SQLValue.Timestamp(category.getUpdateDate()));
			bindVars.add(SQLValue.String(category.getCreatedBy()));

			logger.debug("QUERY - Loading Category :" + sql);
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
		CategoryData category = (CategoryData) datamodel;

		ResultSet rst = null;
		try {
			String sql = UPDATE_CATEGORY;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();

			bindVars.add(SQLValue.String(category.getName()));
			bindVars.add(SQLValue.Long(category.getParentId()));
			bindVars.add(SQLValue.String(category.getDescritpion()));
			bindVars.add(SQLValue.String(category.getImageName()));
			bindVars.add(SQLValue.Long(category.getViewCount()));
			bindVars.add(SQLValue.Timestamp(category.getCreateDate()));
			bindVars.add(SQLValue.Timestamp(category.getUpdateDate()));
			bindVars.add(SQLValue.String(category.getCreatedBy()));
			bindVars.add(SQLValue.Long(category.getId()));

			logger.debug("QUERY - Loading Category :" + sql);
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
		CategoryData category = (CategoryData) datamodel;

		ResultSet rst = null;
		try {
			String sql = READ_CATEGORY;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (category.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(category.getId()));
			}
			if (category.getName() != null) {
				sql += AND + "`NAME` = ? ";
				bindVars.add(SQLValue.String(category.getName()));
			}

			logger.debug("QUERY - Loading Category :" + sql);
			rst = executeQuery(sql, bindVars);
			ArrayList<DataModel> categories = new ArrayList<DataModel>();
			while (!rst.next()) {
				categories.add(loadCategoryVO(null, rst));
			}
			return categories;
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rst);
		}
	}

	@Override
	public int delete() throws DataModelException, TMException, SQLException {
		CategoryData category = (CategoryData) datamodel;

		ResultSet rst = null;
		try {
			String sql = DELETE_CATEGORY;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (category.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(category.getId()));
			}
			if (category.getName() != null) {
				sql += AND + "`NAME` = ? ";
				bindVars.add(SQLValue.String(category.getName()));
			}
			if (category.getParentId() >= 0) {
				sql += AND + "`PARENT_ID` = ? ";
				bindVars.add(SQLValue.Long(category.getParentId()));
			}
			logger.debug("QUERY - Loading Category :" + sql);
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
		CategoryData category = (CategoryData) datamodel;

		ResultSet rst = null;
		try {
			String sql = READ_CATEGORY;
			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (category.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(category.getId()));
			}
			if (category.getName() != null) {
				sql += AND + "`NAME` = ? ";
				bindVars.add(SQLValue.String(category.getName()));
			}
			if (category.getParentId() >= 0) {
				sql += AND + "`SUB_CAT_NAME` = ? ";
				bindVars.add(SQLValue.Long(category.getParentId()));
			}
			logger.debug("QUERY - Loading Category :" + sql);
			rst = executeQuery(sql, bindVars);

			return loadCategoryVO(category, rst);
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
	 * @param category
	 * @param rst
	 * @throws SQLException
	 */
	public DataModel loadCategoryVO(CategoryData category, ResultSet rst)
			throws TMException, SQLException {
		if (!rst.next()) {
			return null;
		}

		if (category == null) {
			category = new CategoryData();
		}
		try {
			category.setId(rst.getLong(ID));
		} catch (FixedValueException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		category.setName(rst.getString(NAME));
		category.setParentId(rst.getLong(PARENT_ID));
		category.setDescritpion(rst.getString(DESCRIPTION));
		category.setImageName(rst.getString(IMG_NAME));
		category.setViewCount(rst.getLong(VIEW_COUNT));
		category.setCreateDate(rst.getTimestamp(CREATE_DATE));
		category.setUpdateDate(rst.getTimestamp(UPDATE_DATE));
		category.setCreatedBy(rst.getString(CREATED_BY));
		category.setLoaded(true);
		return category;
	}

	public static final String READ_CATEGORY = "SELECT * FROM `CATEGORY_DATA` WHERE 1=1 ";
	public static final String DELETE_CATEGORY = "DELETE FROM `CATEGORY_DATA` WHERE 1=1 ";
	public static final String UPDATE_CATEGORY = "UPDATE `CATEGORY_DATA` SET `NAME` = ? ,`PARENT_ID` = ? ,"
			+ "`DESCRIPTION` = ? ,`IMG_NAME` = ? ,`VIEW_COUNT` = ? ,`CREATE_DATE` = ? ,`UPDATE_DATE` = ? ,`CREATED_BY` = ? WHERE `ID` = ?";
	public static final String CREATE_CATEGORY = "INSERT INTO `CATEGORY_DATA` (`NAME`,`PARENT_ID`,`DESCRIPTION`,`IMG_NAME`,`VIEW_COUNT`,`CREATE_DATE`,`UPDATE_DATE`,`CREATED_BY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String NAME = "NAME";
	public static final String PARENT_ID = "PARENT_ID";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String IMG_NAME = "IMG_NAME";
	public static final String VIEW_COUNT = "VIEW_COUNT";
	public static final String CREATE_DATE = "CREATE_DATE";
	public static final String UPDATE_DATE = "UPDATE_DATE";
	public static final String CREATED_BY = "CREATED_BY";
}
