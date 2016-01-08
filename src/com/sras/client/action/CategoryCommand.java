package com.sras.client.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sras.client.utils.Utilities;
import com.sras.datamodel.CategoryData;
import com.sras.datamodel.DataModel;

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
			ArrayList<DataModel> categoreis = serviceBean.getAllCategories();
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
		} catch (Exception e) {
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
			boolean isParent = subCategoriesMap.containsKey(catId);
			ArrayList<DataModel> deals = serviceBean.getAllDeals(catId, null,
					isParent);

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
				Hashtable<String, Long> statsMap = new Hashtable<String, Long>();
				ctx.put("catData", catData);
				boolean isParent = (subCategoryName != null) ? false : true;
				if (subCategoryName != null) {
					ctx.put("parentCategoryName", parentCategoryName);
				}
				statsMap = serviceBean.getCategoryStats(catData.getId(),
						isParent);
				Enumeration<String> keysEnum = statsMap.keys();
				while (keysEnum.hasMoreElements()) {
					String key = keysEnum.nextElement();
					ctx.put(key, statsMap.get(key));
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
}
