package com.maximbrett.spring.rest.controller;


import com.maximbrett.spring.rest.dto.EmployeeDTO;
import com.maximbrett.spring.rest.entity.Employee;
import com.maximbrett.spring.rest.mapper.EmployeeMapper;
import com.maximbrett.spring.rest.service.EmployeeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<EmployeeDTO>> showAllEmployees() {
        List<Employee> employeeList =  employeeService.getAllEmployees();
        List<EmployeeDTO> dtoList = employeeMapper.employeeToEmployeeDTOList(employeeList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeService.getEmployee(id);
        EmployeeDTO dto =  employeeMapper.employeeToEmployeeDTO(employee);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employeeService.saveEmployee(employee);
        EmployeeDTO dto =  employeeMapper.employeeToEmployeeDTO(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/employees")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
        employeeService.saveEmployee(employee);
        EmployeeDTO updateDto = employeeMapper.employeeToEmployeeDTO(employee);
        return ResponseEntity.ok(updateDto);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with id " + id + " has been deleted");
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
