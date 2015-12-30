package com.sras.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.sras.datamodel.DataModel;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class StoreDao extends BaseDao {

	private static Logger logger = Logger.getLogger(StoreDao.class.getName());

	public StoreDao(DataModel datamodel) {
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
		// TODO Auto-generated method stub
		return null;
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
