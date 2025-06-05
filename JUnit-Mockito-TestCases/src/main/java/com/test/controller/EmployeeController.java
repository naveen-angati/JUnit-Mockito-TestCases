package com.test.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Employee;
import com.test.model.ResponseStatus;
import com.test.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController { 

	@Autowired
	private EmployeeService service;

	@PostMapping
	public ResponseEntity<ResponseStatus<Employee>> saveEmployee(@RequestBody Employee employee) {
		Employee saved = service.saveEmployee(employee);
		ResponseStatus<Employee> response = new ResponseStatus<>(200, "SUCCESS", "Employee created successfully", saved,new Date());
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<ResponseStatus<List<Employee>>> getAllEmployees() {
		List<Employee> employees = service.getAllEmployees();
		ResponseStatus<List<Employee>> response = new ResponseStatus<>(200, "SUCCESS","Employee list fetched successfully", employees, new Date());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStatus<Employee>> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = service.getEmployeeById(id);
		ResponseStatus<Employee> response = new ResponseStatus<>(200, "SUCCESS", "Employee fetched successfully",employee, new Date());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStatus<Void>> deleteEmployee(@PathVariable("id") Long id) {
		service.deleteEmployee(id);
		ResponseStatus<Void> response = new ResponseStatus<>(204, "SUCCESS", "Employee deleted successfully", null,new Date());
		return ResponseEntity.ok(response);
	}
}
