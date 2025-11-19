package com.example.learn_system.Controllers;

import com.example.learn_system.Exceptions.BusinessException;
import com.example.learn_system.Exceptions.ConflictException;
import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Services.GroupServiceImpl;
import com.example.learn_system.Services.interfaces.EmployeeService;
import com.example.learn_system.Services.interfaces.GroupService;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import com.example.learn_system.dto.EmployeeDto.SimpleEmployeeDTO;
import com.example.learn_system.dto.GroupDto.CreateGroupDTO;
import com.example.learn_system.dto.GroupDto.GroupDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api/groups")
@Tag(name = "Group controller")
public class GroupController {

    private final GroupService groupService;
    private final EmployeeService employeeService;

    public GroupController(GroupServiceImpl groupService, EmployeeService employeeService) {
        this.groupService = groupService;
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(description = "Get all groups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> result = groupService.getAllGroups();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(description = "Create group")
    public ResponseEntity<?> createGroup(@Valid @RequestBody CreateGroupDTO req) {
        try {
            GroupDTO groupDTO = groupService.createGroup(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(groupDTO);
        } catch (ConflictException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionToJson(ex));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionToJson(ex));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionToJson(ex));
        }
    }

    // TODO: РАЗОБРАТЬСЯ КУДА ВЫНЕСТИ getEmployeesByGroupId
    @GetMapping(value = "{groupId}/members")
    @Operation(description = "Get all group members")
    public ResponseEntity<?> getEmployeesByGroupId(@Valid @PathVariable Long groupId) {
        try {
            List<SimpleEmployeeDTO> employees = employeeService.getAllEmployeesByGroupId(groupId);
            return ResponseEntity.status(HttpStatus.OK).body(employees);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionToJson(ex));
        }
    }

    private static HashMap<String, String> exceptionToJson(BusinessException ex) {
        HashMap<String, String> error = new HashMap<>();
        error.put("errorCode", ex.getErrorCode());
        error.put("message", ex.getMessage());
        return error;
    }
}
