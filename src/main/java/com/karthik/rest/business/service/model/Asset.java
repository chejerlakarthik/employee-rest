package com.karthik.rest.business.service.model;

import java.util.Date;

public class Asset extends RestResource{

	private int assetId;
	private String assetName;
	private String assetDescription;
	private Date assetTaggedDate;
	
	public Asset() {}
	
	public Asset(int assetId, String assetName, String assetDescription) {
		this.assetId = assetId;
		this.assetName = assetName;
		this.assetDescription = assetDescription;
		this.assetTaggedDate = new Date();
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public Date getAssetTaggedDate() {
		return assetTaggedDate;
	}

	public void setAssetTaggedDate(Date assetTaggedDate) {
		this.assetTaggedDate = assetTaggedDate;
	}

}
