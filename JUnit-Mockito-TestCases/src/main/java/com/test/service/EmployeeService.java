package com.test.service;

import java.util.List;

import com.test.entity.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	void deleteEmployee(Long id);
}
