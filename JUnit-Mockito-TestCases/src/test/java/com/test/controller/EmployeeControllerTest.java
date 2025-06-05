package com.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Employee;
import com.test.modal.EmployeeTestDataFactory;
import com.test.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService service;

	@Autowired
	private ObjectMapper objectMapper;

	EmployeeTestDataFactory employeeTestDataFactory;

	@BeforeEach
	void setup() {
		employeeTestDataFactory = new EmployeeTestDataFactory();
	}

	@Test
	void testSaveEmployee() throws Exception {
		Mockito.when(service.saveEmployee(any(Employee.class)))
				.thenReturn(employeeTestDataFactory.getEmployeeDetails());

		mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeTestDataFactory.getEmployeeDetails())))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Employee created successfully"))
				.andExpect(jsonPath("$.data.name").value("Naveen"));
	}
	
	
	

	@Test
	void testGetAllEmployees() throws Exception {
		Mockito.when(service.getAllEmployees()).thenReturn(employeeTestDataFactory.getAllEmpDetails());

		mockMvc.perform(get("/employee")).andExpect(status().isOk()).andExpect(jsonPath("$.data.length()").value(2))
				.andExpect(jsonPath("$.data[0].name").value("Naveen"));
	}

	@Test
	void testGetEmployeeById() throws Exception {
		Mockito.when(service.getEmployeeById(1L)).thenReturn(employeeTestDataFactory.getEmployeeDetails());

		mockMvc.perform(get("/employee/1")).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(200))
				.andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Employee fetched successfully"))
				.andExpect(jsonPath("$.data.id").value(1))
				.andExpect(jsonPath("$.data.name").value(employeeTestDataFactory.getEmployeeDetails().getName()))
				.andExpect(jsonPath("$.data.email").value(employeeTestDataFactory.getEmployeeDetails().getEmail()))
				.andExpect(jsonPath("$.data.department") 
						.value(employeeTestDataFactory.getEmployeeDetails().getDepartment()));
	}

	@Test
	void testDeleteEmployee() throws Exception {
		doNothing().when(service).deleteEmployee(1L);

		mockMvc.perform(delete("/employee/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("SUCCESS")).andExpect(jsonPath("$.code").value(204))
				.andExpect(jsonPath("$.message").value("Employee deleted successfully"));
	}

}
