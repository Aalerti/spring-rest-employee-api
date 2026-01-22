package com.maximbrett.spring.rest.service;

import com.maximbrett.spring.rest.dao.EmployeeDAO;
import com.maximbrett.spring.rest.entity.Employee;
import com.maximbrett.spring.rest.exception_handling.NoSuchEmployeeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() {
        Employee employee1 = new Employee("Alex", "Sigorov", "HR", 800);
        Employee employee2 = new Employee("Bob", "Sam", "HR", 900);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeDAO.getAllEmployees()).thenReturn(employeeList);

        List<Employee> result = employeeServiceImpl.getAllEmployees();
        assertEquals(employeeList, result);
    }

    @Test
    void getAllEmployees_ShouldReturnNull_WhenNoEmployeesFound() {
        when(employeeDAO.getAllEmployees()).thenReturn(null);

        Exception exception = assertThrows(NoSuchEmployeeException.class, () -> employeeServiceImpl.getAllEmployees());
        assertEquals("No employees found", exception.getMessage());
    }

    @Test
    void getEmployee_ShouldReturnEmployee_WhenFound() {
        Employee employeeFromDB = new Employee("Ivan", "Petrov", "IT", 1000);
        employeeFromDB.setId(1L);

        when(employeeDAO.getEmployee(1L)).thenReturn(employeeFromDB);

        Employee actual = employeeServiceImpl.getEmployee(1L);

        assertNotNull(actual);

        assertEquals(employeeFromDB, actual);
    }

    @Test
    void getEmployee_ShouldThrowException_WhenEmployeeNotFound() {
        when(employeeDAO.getEmployee(1L)).thenReturn(null);

        Exception exception = assertThrows(NoSuchEmployeeException.class, () -> {
            Employee actual = employeeServiceImpl.getEmployee(1L);
        });

        assertEquals("No employee found", exception.getMessage());
    }

    @Test
    void saveEmployee_ShouldCallDao() {
        Employee employee = new  Employee("Ivan", "Petrov", "IT", 1000);
        employeeServiceImpl.saveEmployee(employee);

        verify(employeeDAO, times(1)).saveEmployee(employee);
    }

    @Test
    void deleteEmployee_ShouldCallDao() {
        Employee employee = new  Employee("Ivan", "Petrov", "IT", 1000);
        employee.setId(1L);

        when(employeeDAO.getEmployee(1L)).thenReturn(employee);

        employeeServiceImpl.deleteEmployee(1L);

        verify(employeeDAO, times(1)).deleteEmployee(1L);
    }

    @Test
    void deleteNullEmployee_ShouldThrowException_WhenEmployeeNotFound() {
        when(employeeDAO.getEmployee(1L)).thenReturn(null);
        Exception exception = assertThrows(NoSuchEmployeeException.class, () -> {
            employeeServiceImpl.deleteEmployee(1L);
        });
        assertEquals("No employee found", exception.getMessage());

        verify(employeeDAO, times(0)).deleteEmployee(1L);
    }


}