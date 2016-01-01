package com.sras.client.action;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sras.client.utils.Utilities;
import com.sras.dao.BaseDao;
import com.sras.dao.CategoryDao;
import com.sras.dao.DealViewDao;
import com.sras.datamodel.CategoryData;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealViewData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class CategoryCommand extends Command {
	private String TEMPLATE_NAME = "category.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	static Hashtable<Long, String> subCategoriesMap = new Hashtable<Long, String>();
	static Hashtable<String, CategoryData> categoriesMap = new Hashtable<String, CategoryData>();

	static {
		loadCategories();
	}

	public static void loadCategories() {
		try {
			ArrayList<DataModel> subList = new ArrayList<DataModel>();

			CategoryData catData = new CategoryData();
			CategoryDao catDao = new CategoryDao(catData);
			ArrayList<DataModel> categoreis = catDao.enumerate();
			for (DataModel cat : categoreis) {
				CategoryData cData = (CategoryData) cat;
				if (cData.getParentId() < 0) {
					subCategoriesMap.put(cData.getId(), "");
				} else {
					String subCatList = subCategoriesMap.get(cData
							.getParentId());
					if (subCatList != null) {
						subCatList += ", " + cData.getId();
					} else {
						subCatList = Long.toString(cData.getId());
					}
					subCategoriesMap.put(cData.getParentId(), subCatList);
				}
				categoriesMap.put(cData.getName(), cData);
			}
		} catch (DataModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Hashtable<String, CategoryData> getCategories() {
		return categoriesMap;
	}

	public CategoryCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		try {
			Long catId = Utilities.getLongFromRequest(request, "cid");
			DealViewData deal = new DealViewData();
			if (subCategoriesMap.containsKey(catId)) {
				deal.setParentCategoryId(catId);
			} else {
				deal.setCategoryId(catId);
			}
			DealViewDao dao = new DealViewDao(deal);
			ArrayList<DataModel> deals = dao.enumerate();
			String jsonStr = "No deals available!";
			if (deals != null && deals.size() > 0) {
				Gson gson = new GsonBuilder().create();
				jsonStr = gson.toJson(deals, ArrayList.class);
			}
			ctx.put("ajax_response_data", jsonStr);
		} catch (Exception e) {
			ctx.put("ajax_response_data", "Failed while retrieving deals!!");
		}
		return "ajax_template.vm";
	}

	public String doGet() throws Exception {
		// To handle URL format like .../category/Accessories
		String requestURI = (String) ctx.get("requestURI");
		requestURI = URLDecoder.decode(requestURI, "UTF-8");
		int i = requestURI.indexOf('/');
		String parentCategoryName = null, subCategoryName = null;
		if (i > 0 && requestURI.indexOf("category") == 0) {
			requestURI = requestURI.substring(i + 1);
			if (requestURI.contains("/")) {
				String[] names = requestURI.split("/");
				parentCategoryName = names[0];
				subCategoryName = names[1];
			} else {
				parentCategoryName = requestURI;
			}
			String name = (subCategoryName == null) ? parentCategoryName
					: subCategoryName;
			CategoryData catData = categoriesMap.get(name);
			if (catData != null) {
				ctx.put("catData", catData);
				if (subCategoryName != null) {
					ctx.put("parentCategoryName", parentCategoryName);
					getCategoryStats(catData.getId(), false);
				}
				else
				{
					getCategoryStats(catData.getId(), true);
				}
				TEMPLATE_NAME = "category.vm";
			} else {
				TEMPLATE_NAME = "error.vm";
			}
		}
		// MailUtils.sendMail("itsras@gmail.com", "Test Mail", "Test Mail");
		return TEMPLATE_NAME;
	}

	public String doAjaxPost() throws Exception {
		return "ajax_template.vm";
	}

	public void getCategoryStats(long categoryId, boolean isParent)
			throws Exception {
		String sql = "SELECT DEAL_TYPE, COUNT(*) FROM DEAL_DATA_VW WHERE IS_ACTIVE = ? ";

		if (isParent) {
			sql += " AND PARENT_ID = ? ";
		} else {
			sql += " AND CATEGORY_ID = ? ";
		}

		Connection con = BaseDao.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBoolean(1, true);
		ps.setLong(2, categoryId);
		ResultSet rst = ps.executeQuery();
		long totalCount = 0;
		while (rst.next()) {
			String codeType = rst.getString(1);
			Long count = rst.getLong(2);
			count = (count == null) ? new Long(0) : count;
			ctx.put(codeType, count.longValue());
			totalCount += count;
		}
		ctx.put("totalCount", totalCount);
	}
}
