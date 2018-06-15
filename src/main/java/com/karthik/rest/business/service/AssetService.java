package com.karthik.rest.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.karthik.rest.business.service.model.Asset;
import com.karthik.rest.dao.AssetDao;

public class AssetService {
	
	public List<Asset> readAll(Long empId) {
		return new ArrayList<Asset>(AssetDao.assets.get(empId).values());
	}

	public Asset read(Long empId, Integer assetId) {
		return AssetDao.assets.get(empId).get(assetId);
	}
	
	public Asset tagAssetToEmployee(Long empId, Asset asset) {
		asset.setAssetTaggedDate(new Date());
		Map<Integer, Asset> empAssets = AssetDao.assets.get(empId);
		empAssets.put(asset.getAssetId(), asset);
		System.out.println("EmpAssets size: " + empAssets.size());
		AssetDao.assets.put(empId, empAssets);
		return asset;
	}

}
