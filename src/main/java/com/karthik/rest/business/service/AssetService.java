package com.karthik.rest.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.karthik.rest.business.service.model.Asset;
import com.karthik.rest.dao.AssetDao;
import com.karthik.rest.exception.DoesNotExistException;

public class AssetService {
	
	public List<Asset> readAll(Long empId) throws DoesNotExistException {
		Map<Integer, Asset> employeeAssets = AssetDao.assets.get(empId);
		if (employeeAssets == null) {
			throw new DoesNotExistException("No assets tagged to employee ID " + empId);
		}
		return new ArrayList<Asset>(employeeAssets.values());
	}

	public Asset read(Long empId, Integer assetId) throws DoesNotExistException {
		Map<Integer, Asset> employeeAssets = AssetDao.assets.get(empId);
		if (employeeAssets == null) {
			throw new DoesNotExistException("For empID " + empId + " : Employee not found (or) no assets tagged");
		}
		Asset asset = employeeAssets.get(assetId);
		if (asset == null) {
			throw new DoesNotExistException("No asset found with ID " + assetId + " tagged to empId " + empId);
		}
		return asset;
	}
	
	public Asset tagAssetToEmployee(Long empId, Asset asset) {
		asset.setAssetTaggedDate(new Date());
		Map<Integer, Asset> empAssets = AssetDao.assets.get(empId);
		if (empAssets == null) {
			empAssets = new HashMap<Integer, Asset>();
		}
		empAssets.put(asset.getAssetId(), asset);
		AssetDao.assets.put(empId, empAssets);
		return asset;
	}

}
