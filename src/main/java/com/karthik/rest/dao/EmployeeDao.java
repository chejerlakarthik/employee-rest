package com.karthik.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.karthik.rest.business.service.model.Employee;

public class EmployeeDao {
	
	public static Map<Long, Employee> employees = new HashMap<Long, Employee>();
	
	public static List<Employee> getEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		empList.addAll(employees.values());
		return empList;
	}

}
