package com.example.learn_system.Controllers;

import com.example.learn_system.Services.GroupServiceImpl;
import com.example.learn_system.dto.GroupDto.GroupDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/groups")
@Tag(name = "Group controller")
public class GroupController {

    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @Operation(description = "Get all groups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> result = groupService.getAllGroups();

        return ResponseEntity.ok(result);
    }
}
