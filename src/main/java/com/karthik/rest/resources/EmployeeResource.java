package com.karthik.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.karthik.rest.business.service.EmployeeService;
import com.karthik.rest.business.service.model.Employee;

@Path("/employees")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class EmployeeResource {

	private EmployeeService service = new EmployeeService();

	@GET
	public List<Employee> getAll(@QueryParam(value = "year") Integer year,
								 @QueryParam(value = "start") Integer start,
								 @QueryParam(value = "size") Integer size) {
		// Filtering parameters
		if (year != null && year > 0) {
			return service.readAllFilteredByYear(year);
		}
		// Pagination parameters
		if (start != null && size != null && start > 0 && size > 0) {
			return service.readAllPaginated(start, size);
		}

		return service.readAll();
	}

	@GET
	@Path("/{empId}")
	public Employee get(@PathParam(value = "empId") Long empId) {
		return service.read(empId);
	}

	@POST
	public Employee add(Employee employee) {
		return service.create(employee);
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

}
