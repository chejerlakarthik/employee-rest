package com.karthik.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.karthik.rest.business.service.AssetService;
import com.karthik.rest.business.service.model.Asset;
import com.karthik.rest.exception.DoesNotExistException;

@Path("")
public class AssetResource {

	private AssetService assetService = new AssetService();

	@GET
	public List<Asset> getAssetsForEmployee(@PathParam(value = "empId") Long empId, 
											@Context UriInfo uriInfo) throws DoesNotExistException {
		List<Asset> assets = new ArrayList<Asset>();
		
		// HATEOAS implementation - First reset the links
		for (Asset asset : assetService.readAll(empId)) {
			asset.resetLinks();

			asset.addLink(linkToSelf(uriInfo, String.valueOf(empId)), "self");
			asset.addLink(linkToEmployee(uriInfo, String.valueOf(empId)), "employee");
			assets.add(asset);
		}
		return assets;
	}
	
	@GET
	@Path("/{assetId}")
	public Asset getAssetByIdForEmployee(@PathParam(value = "empId") Long empId, 
										 @PathParam(value = "assetId") Integer assetId,
										 @Context UriInfo uriInfo) throws DoesNotExistException {
		Asset asset = assetService.read(empId, assetId);
		
		// HATEOAS implementation - First reset the links
		asset.resetLinks();
		
		// Now, add links to self and employee resource
		asset.addLink(linkToSelf(uriInfo, String.valueOf(empId), String.valueOf(assetId)), "self");
		asset.addLink(linkToEmployee(uriInfo, String.valueOf(empId)), "employee");
		
		return asset;
	}
	
	@POST
	public Asset tagAssetToEmployee(@PathParam(value = "empId") Long empId, Asset asset) {
		return assetService.tagAssetToEmployee(empId, asset);
	}
	
	/**
	 * Creates a link to the assets collection resource
	 * @param uriInfo
	 * @param empId
	 * @return
	 */
	private String linkToSelf(UriInfo uriInfo, String empId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(EmployeeResource.class, "getAssetResource")
					  .resolveTemplate("empId", empId)
					  .build()
					  .toString();
	}
	
	/**
	 * Creates a link to the individual asset resource
	 * @param uriInfo
	 * @param empId
	 * @param assetId
	 * @return
	 */
	private String linkToSelf(UriInfo uriInfo, String empId, String assetId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(EmployeeResource.class, "getAssetResource")
					  .path(assetId)
					  .resolveTemplate("empId", empId)
					  .build()
					  .toString();
	}
	
	/**
	 * Creates a link to the asset's employee resource. (Asset is a sub-resource of Employee)
	 * @param uriInfo
	 * @param empId
	 * @return
	 */
	private String linkToEmployee(UriInfo uriInfo, String empId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(empId)
					  .build()
					  .toString();
	}

}
