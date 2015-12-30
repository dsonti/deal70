package com.sras.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.sras.datamodel.DataModel;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class DealDao extends BaseDao {

	private static Logger logger = Logger.getLogger(DealDao.class.getName());

	public DealDao(DataModel datamodel) {
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

}
