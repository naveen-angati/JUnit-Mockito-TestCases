package com.test.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.entity.Employee;
import com.test.exception.CustomException;
import com.test.modal.EmployeeTestDataFactory;
import com.test.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeerepository;

	@InjectMocks
	EmployeeServiceImpl employeeService;

	EmployeeTestDataFactory employeeTestDataFactory;

	@BeforeEach
	void setUp() {
		employeeTestDataFactory = new EmployeeTestDataFactory(); 
	}

	@Test
	void saveEmployeeSuccessTest() {
		when(employeerepository.save(employeeTestDataFactory.getEmployeeDetails())).thenReturn(employeeTestDataFactory.getEmployeeDetails());
		Employee employeeResponse = employeeService.saveEmployee(employeeTestDataFactory.getEmployeeDetails());
		Assertions.assertEquals(1, employeeResponse.getId());
	}

	@Test
	void getAllEmployeesTest() {
		when(employeerepository.findAll()).thenReturn(employeeTestDataFactory.getAllEmpDetails());
		List<Employee> allEmpData = employeeService.getAllEmployees();
		Assertions.assertEquals(allEmpData.size(), employeeTestDataFactory.getAllEmpDetails().size());
	}

	
	@Test
	void testGetEmployeeById_Success() {
		when(employeerepository.findById(1L)).thenReturn(Optional.of(employeeTestDataFactory.getEmployeeDetails()));

		Employee result = employeeService.getEmployeeById(1L);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(employeeTestDataFactory.getEmployeeDetails().getName(), result.getName());
	}

	@Test
    void testGetEmployeeById_NotFound() {
        when(employeerepository.findById(99L)).thenReturn(Optional.empty());

        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
            employeeService.getEmployeeById(99L);
        });

        Assertions.assertEquals("Employee not found with ID: 99", exception.getMessage());
        //Assertions.assertEquals(400, exception.getCode()); // assuming you have getCode() in CustomException
    }

	@Test
	void testDeleteById() {
		Long idToDelete = 1L;
		doNothing().when(employeerepository).deleteById(idToDelete);
		employeeService.deleteEmployee(idToDelete);
		Mockito.verify(employeerepository, times(1)).deleteById(idToDelete);
	}
	
	@Test
    void testPrivateFormatEmployeeName() throws Exception {
        // Get the private method by name and parameter types
        Method method = EmployeeServiceImpl.class.getDeclaredMethod("formatEmployeeName", String.class);
        
        // Make it accessible
        method.setAccessible(true);

        // Invoke the private method with argument
        String result = (String) method.invoke(employeeService, "naveen");

        // Assert expected result
        Assertions.assertEquals("NAVEEN", result); 
    }

}
