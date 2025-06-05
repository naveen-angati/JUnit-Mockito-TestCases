package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Employee;
import com.test.exception.CustomException;
import com.test.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll(); 
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return repository.findById(id).orElseThrow(() -> new CustomException("Employee not found with ID: " + id, 400));
	}

	@Override
	public void deleteEmployee(Long id) {
		repository.deleteById(id); 
	}
	
    private String formatEmployeeName(String name) {
        return name.toUpperCase();  
    }

}
