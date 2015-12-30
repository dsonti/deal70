package com.sras.client.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.sras.client.utils.Utilities;
import com.sras.dao.CategoryDao;
import com.sras.dao.DealDao;
import com.sras.datamodel.CategoryData;
import com.sras.datamodel.DataModel;
import com.sras.datamodel.DealData;
import com.sras.datamodel.exceptions.DataModelException;
import com.sras.datamodel.exceptions.TMException;

public class CategoryCommand extends Command {
	private static String TEMPLATE_NAME = "categories.vm";
	protected static Category log = Category.getInstance(MainCommand.class);

	static Hashtable<Long, String> subCategoriesMap = new Hashtable<Long, String>();
	static Hashtable<Long, CategoryData> categoriesMap = new Hashtable<Long, CategoryData>();

	static {
		loadCategories();
	}

	public static void loadCategories() {
		try {
			CategoryData catData = new CategoryData();
			CategoryDao catDao = new CategoryDao(catData);
			ArrayList<DataModel> categoreis = catDao.enumerate();
			for (DataModel cat : categoreis) {
				CategoryData cData = (CategoryData) cat;
				if (cData.getParentId() < 0) {
					subCategoriesMap.put(cData.getId(), "");
				} else {
					String subCatList = subCategoriesMap.get(cData.getParentId());
					if (subCatList != null) {
						subCatList += ", " + cData.getId();
					} else {
						subCatList = Long.toString(cData.getId());
					}
					subCategoriesMap.put(cData.getParentId(), subCatList);
				}
				categoriesMap.put(cData.getId(), cData);
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

	public CategoryCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
	}

	public String doAjaxGet() {
		long categoryId = Utilities.getLongFromRequest(request, "ct");
		try {
			DealData deal = new DealData();
			if (subCategoriesMap.containsKey(categoryId)) {
				deal.setSubCategoryIds(subCategoriesMap.get(categoryId) + ", "
						+ categoryId);
			} else {
				deal.setCategoryId(categoryId);
			}
			DealDao dao = new DealDao(deal);
			ArrayList deals = dao.enumerate();
			ctx.put("ajax_response_data", "Ajax call successful!");
		} catch (Exception e) {
			ctx.put("ajax_response_data", "Failed while retrieving deals!!");

		}
		return "ajax_template.vm";
	}

	public String doGet() throws Exception {
		String hostName = Utilities.getRemoteHostName(request);
		ctx.put("hostName", hostName);
		long categoryId = Utilities.getLongFromRequest(request, "ct");
		CategoryData catData = categoriesMap.get(categoryId);
		ctx.put("catData", catData);
		CategoryData parentCatData = categoriesMap.get(catData.getParentId());
		if (parentCatData != null) {
			ctx.put("parentCatData", parentCatData);
		}
		// MailUtils.sendMail("itsras@gmail.com", "Test Mail", "Test Mail");
		return TEMPLATE_NAME;
	}

	public String doAjaxPost() throws Exception {
		return "ajax_template.vm";
	}
}
