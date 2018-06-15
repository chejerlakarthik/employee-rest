package com.karthik.rest.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.karthik.rest.business.service.model.Employee;
import com.karthik.rest.dao.EmployeeDao;
import com.karthik.rest.exception.DoesNotExistException;

public class EmployeeService {
	
	public EmployeeService() {
		EmployeeDao.employees.put(1L, new Employee(1L, "Karthik"));
		EmployeeDao.employees.put(2L, new Employee(2L, "Aarthi"));
	}

	public Employee add(Employee employee) {
		employee.setLastModified(new Date());
		EmployeeDao.employees.put(employee.getEmpId(), employee);
		return employee;
	}
	
	public Employee read(Long empId) throws DoesNotExistException {
		Employee employee = EmployeeDao.employees.get(empId);
		if (employee == null)
			throw new DoesNotExistException("Employee " + empId +" does not exist");
		return employee;
	}
	
	public List<Employee> readAll() {
		return EmployeeDao.getEmployees();
	}
	
	public List<Employee> readAllFilteredByYear(Integer year) {
		assert (year != null && year > 0);
		List<Employee> employeesByYear = new ArrayList<Employee>();

		for (Employee employee : EmployeeDao.getEmployees()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(employee.getLastModified());
			if (cal.get(Calendar.YEAR) == year) {
				employeesByYear.add(employee);
			}
		}
		return employeesByYear;
	}
	
	public List<Employee> readAllPaginated(Integer start, Integer pagesize) {
		assert (start > 0 && pagesize > 0);
		
		if (pagesize > EmployeeDao.getEmployees().size())
			pagesize = EmployeeDao.getEmployees().size();
		
		return EmployeeDao.getEmployees().subList(start-1, start + pagesize -1);
	}
	
	public Employee update(Long empId, Employee employee) {
		Employee emp = EmployeeDao.employees.get(empId);
		
		if (emp != null && employee instanceof Employee) {
			employee.setLastModified(new Date());
			EmployeeDao.employees.put(empId, employee);
			return employee;
		} 
		else {
			System.err.println("Could not find employee");
		}
		return null;
	}
	
	public void delete(Long empId) {
		EmployeeDao.employees.remove(empId);
	}

}
