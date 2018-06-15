package com.karthik.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.karthik.rest.exception.DoesNotExistException;
import com.karthik.rest.exception.ErrorEntity;

@Provider
public class DoesNotExistExceptionMapper implements ExceptionMapper<DoesNotExistException> {

	@Override
	public Response toResponse(DoesNotExistException ex) {
		ErrorEntity error = new ErrorEntity(404, ex.getMessage());
		Response response = Response.status(Status.NOT_FOUND).entity(error).build();
		return response;
	}

}
