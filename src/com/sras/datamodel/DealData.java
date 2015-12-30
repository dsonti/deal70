package com.sras.datamodel;

import java.util.Date;

public class DealData extends WithId {

	long categoryId;
	long storeId;
	String location;
	String title;
	String description1;
	String description2;
	String code;
	String stepsToConsume;
	long viewCount;
	Boolean isActive;
	String dealImage;
	Date expiryDate;
	Date createDate;
	Date updateDate;
	String createdBy;
	String subCategoryIds;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStepsToConsume() {
		return stepsToConsume;
	}

	public void setStepsToConsume(String stepsToConsume) {
		this.stepsToConsume = stepsToConsume;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getDealImage() {
		return dealImage;
	}

	public void setDealImage(String dealImage) {
		this.dealImage = dealImage;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSubCategoryIds() {
		return subCategoryIds;
	}

	public void setSubCategoryIds(String subCategoryIds) {
		this.subCategoryIds = subCategoryIds;
	}
}
