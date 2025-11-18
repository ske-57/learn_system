package com.example.learn_system.Services.interfaces;

import com.example.learn_system.dto.EmployeeDto.EmployeeCreateDTO;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import com.example.learn_system.dto.EmployeeDto.SimpleEmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO createEmployee(EmployeeCreateDTO req);
}
