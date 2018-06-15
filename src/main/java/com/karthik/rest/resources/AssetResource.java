package com.karthik.rest.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.karthik.rest.business.service.AssetService;
import com.karthik.rest.business.service.model.Asset;

@Path("/")
public class AssetResource {

	private AssetService assetService = new AssetService();

	@GET
	public List<Asset> getAssetsForEmployee(@PathParam(value = "empId") Long empId) {
		return assetService.readAll(empId);
	}
	
	@GET
	@Path("/{assetId}")
	public Asset getAssetByIdForEmployee(@PathParam(value = "empId") Long empId, 
										 @PathParam(value = "assetId") Integer assetId) {
		return assetService.read(empId, assetId);
	}
	
	@POST
	public Asset tagAssetToEmployee(@PathParam(value = "empId") Long empId, Asset asset) {
		return assetService.tagAssetToEmployee(empId, asset);
	}
}
