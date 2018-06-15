package com.karthik.rest.params;

import javax.ws.rs.QueryParam;

public class EmployeeBeanParam {

	@QueryParam(value = "year")
	Integer year;
	@QueryParam(value = "start")
	Integer start;
	@QueryParam(value = "size")
	Integer size;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
