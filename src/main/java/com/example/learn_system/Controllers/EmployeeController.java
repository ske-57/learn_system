package com.example.learn_system.Controllers;

import com.example.learn_system.Services.EmployeeServiceImpl;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import com.example.learn_system.dto.EmployeeDto.SimpleEmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }
}
