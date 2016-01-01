package com.sras.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealViewData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class DealViewDao extends BaseDao {

	private static Logger logger = Logger
			.getLogger(DealViewDao.class.getName());

	public DealViewDao(DataModel datamodel) {
		super(datamodel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long create() throws DataModelException, TMException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update() throws DataModelException, TMException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DataModel> enumerate() throws DataModelException,
			TMException, SQLException {
		DealViewData deal = (DealViewData) datamodel;

		ResultSet rs = null;
		try {

			String sql = "SELECT ID,STORE_ID,CATEGORY_ID,LOCATION,TITLE,DESCRIPTION1,DESCRIPTION2,"
					+ "DEAL_CODE,DEAL_TYPE,STEPS_TO_CONSUME,VIEW_COUNT,IS_ACTIVE,DEAL_IMG_NAME,DEAL_URL,CREATE_DATE,EXPIRY_DATE,"
					+ "UPDATE_DATE,CREATED_BY,PRICE,DISCOUNT,DISCOUNTED_PRICE,"
					+ "STORE_NAME,STORE_IMG_NAME,STORE_URL,CATEGORY_NAME,PARENT_ID FROM DEAL_DATA_VW WHERE 1=1 ";

			Collection<SQLValue> bindVars = new ArrayList<SQLValue>();
			if (deal.getId() > 0) {
				sql += AND + "`ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getId()));
			}
			if (deal.getStoreId() > 0) {
				sql += AND + "`STORE_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getStoreId()));
			}
			if (deal.getStoreName() != null) {
				sql += AND + "`STORE_NAME` = ? ";
				bindVars.add(SQLValue.String(deal.getStoreName()));
			}
			if (deal.getCategoryId() > 0) {
				sql += AND + "`CATEGORY_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getCategoryId()));
			}
			if (deal.getParentCategoryId() > 0) {
				sql += AND + "`PARENT_ID` = ? ";
				bindVars.add(SQLValue.Long(deal.getParentCategoryId()));
			}
			if (deal.getCategoryName() != null) {
				sql += AND + "`CATEGORY_NAME` = ? ";
				bindVars.add(SQLValue.String(deal.getCategoryName()));
			}
			if (deal.getLocation() != null) {
				sql += AND + "`LOCATION` = ? ";
				bindVars.add(SQLValue.String(deal.getLocation()));
			}
			if (deal.getTitle() != null) {
				sql += AND + "`NAME` like ? ";
				bindVars.add(SQLValue.String(deal.getTitle()));
			}
			sql += AND + "`IS_ACTIVE` = ? ";
			bindVars.add(SQLValue.Boolean(deal.isActive));
			
			sql += ORDER_BY + "`UPDATE_DATE` desc ";
			logger.debug("QUERY - Loading Store :" + sql);
			rs = executeQuery(sql, bindVars);

			DealViewData dealView = new DealViewData();
			ArrayList<DataModel> dealsView = new ArrayList<DataModel>();
			while (rs.next()) {
				dealView = new DealViewData();
				dealView.setId(rs.getLong("ID"));
				dealView.setCategoryId(rs.getLong("CATEGORY_ID"));
				dealView.setStoreId(rs.getLong("STORE_ID"));
				dealView.setLocation(rs.getString("LOCATION"));
				dealView.setTitle(rs.getString("TITLE"));
				dealView.setDescription1(rs.getString("DESCRIPTION1"));
				dealView.setDescription2(rs.getString("DESCRIPTION2"));
				dealView.setCode(rs.getString("DEAL_CODE"));
				dealView.setDealType(rs.getString("DEAL_TYPE"));
				dealView.setStepsToConsume(rs.getString("STEPS_TO_CONSUME"));
				dealView.setViewCount(rs.getLong("VIEW_COUNT"));
				dealView.setIsActive(rs.getBoolean("IS_ACTIVE"));
				dealView.setDealImage(rs.getString("DEAL_IMG_NAME"));
				dealView.setDealUrl(rs.getString("DEAL_URL"));
				dealView.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
				dealView.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				dealView.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
				dealView.setCreatedBy(rs.getString("CREATED_BY"));
				dealView.setPrice(rs.getString("PRICE"));
				dealView.setDiscount(rs.getString("DISCOUNT"));
				dealView.setDiscountedPrice(rs.getString("DISCOUNTED_PRICE"));
				dealView.setCategoryName(rs.getString("CATEGORY_NAME"));
				dealView.setParentCategoryId(rs.getLong("PARENT_ID"));
				dealView.setStoreName(rs.getString("STORE_NAME"));
				dealView.setImageName(rs.getString("STORE_IMG_NAME"));
				dealView.setUrl(rs.getString("STORE_URL"));
				dealsView.add(dealView);
			}
			return dealsView;
		} catch (SQLException sql) {
			logger.error("SQL-Exception", sql);
			throw new TMException("SQL-Exception", sql.getLocalizedMessage());
		} finally {
			close(null, rs);
		}
	}

	@Override
	public int delete() throws DataModelException, TMException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataModel read() throws DataModelException, TMException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateRules() throws TMException {
		// TODO Auto-generated method stub
		return false;
	}
}
