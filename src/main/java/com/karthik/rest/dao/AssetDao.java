package com.karthik.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.karthik.rest.business.service.model.Asset;

public class AssetDao {

	public static Map<Long, Map<Integer, Asset>> assets = new HashMap<Long, Map<Integer,Asset>>();
	
	static {
		Map<Integer, Asset> assetsMap = new HashMap<Integer, Asset>();
		assetsMap.put(1001, new Asset(1001, "Laptop", "Dell Latitude"));
		assetsMap.put(1002, new Asset(1002, "Mobile", "Apple iPhone 6S"));
		
		AssetDao.assets.put(1L, assetsMap);
		
		assetsMap = new HashMap<Integer, Asset>();
		assetsMap.put(2001, new Asset(2001, "Laptop", "HP Spectre"));
		assetsMap.put(2002, new Asset(2002, "Mobile", "OnePlus 6"));
		
		AssetDao.assets.put(2L, assetsMap);
	}

	public static List<Asset> getEmployeeAssets(Long empId) {
		List<Asset> employeeAssets = new ArrayList<Asset>();
		employeeAssets.addAll(assets.get(empId).values());
		return employeeAssets;
	}

}
