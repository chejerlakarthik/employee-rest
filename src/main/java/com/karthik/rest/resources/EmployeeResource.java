package com.karthik.rest.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.karthik.rest.business.service.EmployeeService;
import com.karthik.rest.business.service.model.Employee;
import com.karthik.rest.exception.DoesNotExistException;
import com.karthik.rest.params.EmployeeBeanParam;

@Path("/employees")
@Produces(value = { MediaType.APPLICATION_JSON })
@Consumes(value = { MediaType.APPLICATION_JSON })
public class EmployeeResource {

	private EmployeeService service = new EmployeeService();

	@GET
	public List<Employee> getAll(@BeanParam EmployeeBeanParam paramsBean, @Context UriInfo uriInfo) {
		// Filtering parameters
		if (paramsBean.getYear() != null && paramsBean.getYear() > 0) {
			return service.readAllFilteredByYear(paramsBean.getYear());
		}
		// Pagination parameters
		if (paramsBean.getStart() != null && paramsBean.getSize() != null 
				&& paramsBean.getStart() > 0 && paramsBean.getSize() > 0) {
			return service.readAllPaginated(paramsBean.getStart(), paramsBean.getSize());
		}

		List<Employee> employees = new ArrayList<Employee>();
		for (Employee employee : service.readAll()) {
			String eid = String.valueOf(employee.getEmpId());
			
			//First reset the links
			employee.resetLinks();
			
			employee.addLink(linkToSelf(uriInfo, eid), "self");
			employee.addLink(linkToAssets(uriInfo, eid), "assets");
			employees.add(employee);
		}
		return employees;
	}

	@GET
	@Path("/{empId}")
	public Employee get(@PathParam(value = "empId") Long empId, @Context UriInfo uriInfo) throws DoesNotExistException {
		Employee employee = service.read(empId);
		String eid = String.valueOf(empId);
		
		//First reset the links
		employee.resetLinks();
				
		employee.addLink(linkToSelf(uriInfo, eid), "self");
		employee.addLink(linkToAssets(uriInfo, eid), "assets");
		return employee;
	}

	private String linkToSelf(UriInfo uriInfo, String empId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(empId)
					  .build()
					  .toString();
	}
	
	private String linkToAssets(UriInfo uriInfo, String empId) {
		return uriInfo.getBaseUriBuilder()
					  .path(EmployeeResource.class)
					  .path(EmployeeResource.class, "getAssetResource")
					  .path(AssetResource.class)
					  .resolveTemplate("empId", empId)
					  .build()
					  .toString();
	}

	@POST
	public Response add(Employee employee, @Context UriInfo uriInfo) {
		Employee newEmployee = service.add(employee);
		String newId = String.valueOf(newEmployee.getEmpId());
		URI newResourceLocation = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(newResourceLocation)
					   .entity(newEmployee)
					   .build();
	}

	@PUT
	@Path("/{empId}")
	public Employee update(@PathParam(value="empId") Long empId, Employee employee) {
		return service.update(empId, employee);
	}

	@DELETE
	@Path("/{empId}")
	public void delete(Long empId) {
		service.delete(empId);
	}
	
	@Path("/{empId}/assets")
	public AssetResource getAssetResource() {
		return new AssetResource();
	}

}
