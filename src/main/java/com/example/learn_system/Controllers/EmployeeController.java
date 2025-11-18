package com.example.learn_system.Controllers;

import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Services.EmployeeServiceImpl;
import com.example.learn_system.dto.EmployeeDto.EmployeeCreateDTO;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/employees")
@Tag(name = "Employee controller")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(description = "Get all employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> result = employeeService.getAllEmployees();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(description = "Create new employee")
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeCreateDTO req) {
        try {
            EmployeeDTO created = employeeService.createEmployee(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ValidationException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("errorCode", ex.getErrorCode());
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("errorCode", ex.getErrorCode());
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

}
