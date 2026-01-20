package com.maximbrett.spring.rest.mapper;

import com.maximbrett.spring.rest.dto.EmployeeDTO;
import com.maximbrett.spring.rest.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> employeeToEmployeeDTOList(List<Employee> employees);
}
