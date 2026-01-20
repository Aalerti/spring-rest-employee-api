package com.maximbrett.spring.rest.dao;

import com.maximbrett.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployees();

    public Employee getEmployee(Long id);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(Long id);
}
