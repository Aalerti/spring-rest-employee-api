package com.maximbrett.spring.rest.service;

import com.maximbrett.spring.rest.dao.EmployeeDAO;
import com.maximbrett.spring.rest.entity.Employee;
import com.maximbrett.spring.rest.exception_handling.NoSuchEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeDAO.getAllEmployees();
        if (employeeList == null || employeeList.isEmpty()) throw new NoSuchEmployeeException("No employees found");
        return employeeList;
    }

    @Override
    @Transactional
    public Employee getEmployee(Long id) {
        Employee employee = employeeDAO.getEmployee(id);
        if (employee == null) throw new NoSuchEmployeeException("No employee found");
        return employee;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeDAO.getEmployee(id);
        if (employee == null) throw new NoSuchEmployeeException("No employee found");
        employeeDAO.deleteEmployee(id);
    }
}
