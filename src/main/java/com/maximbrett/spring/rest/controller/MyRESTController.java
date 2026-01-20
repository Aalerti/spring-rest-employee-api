package com.maximbrett.spring.rest.controller;

import com.maximbrett.spring.rest.dao.EmployeeDAO;
import com.maximbrett.spring.rest.dto.EmployeeDTO;
import com.maximbrett.spring.rest.entity.Employee;
import com.maximbrett.spring.rest.exception_handling.EmployeeIncorrectData;
import com.maximbrett.spring.rest.exception_handling.NoSuchEmployeeException;
import com.maximbrett.spring.rest.mapper.EmployeeMapper;
import com.maximbrett.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public MyRESTController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> showAllEmployees() {
        List<Employee> employeeList =  employeeService.getAllEmployees();
        return employeeMapper.employeeToEmployeeDTOList(employeeList);
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable long id) {
        Employee employee = employeeService.getEmployee(id);
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    @PostMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employeeService.saveEmployee(employee);
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    @PutMapping("/employees")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employeeService.saveEmployee(employee);
        return employeeMapper.employeeToEmployeeDTO(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " has been deleted";
    }


    /*private EmployeeDTO converToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setSalary(employee.getSalary());
        return employeeDTO;
    }

    private Employee converToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }*/
}
