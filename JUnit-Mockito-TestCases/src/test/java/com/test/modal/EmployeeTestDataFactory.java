package com.test.modal;

import java.util.Arrays;
import java.util.List;

import com.test.entity.Employee;

public class EmployeeTestDataFactory {

	public Employee getEmployeeDetails() {
		return new Employee(1L, "Naveen", "naveen@gmail.com", "Dev"); 
	}

	public List<Employee> getAllEmpDetails() {
		return Arrays.asList(new Employee(1L, "Naveen", "naveen@gmail.com", "Dev"),
				new Employee(2L, "Kumar", "kumar@gmail.com", "QA"));
	}

}
