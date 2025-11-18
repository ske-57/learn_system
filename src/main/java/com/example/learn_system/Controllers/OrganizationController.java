package com.example.learn_system.Controllers;

import com.example.learn_system.Services.OrganizationServiceImpl;
import com.example.learn_system.dto.OrganizationDto.OrganizationDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/organizations")
public class OrganizationController {

    private final OrganizationServiceImpl organizationService;

    public OrganizationController(OrganizationServiceImpl organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    @Operation(description = "Get all organizations")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        List<OrganizationDTO> result = organizationService.getAllOrganizations();

        return ResponseEntity.ok(result);
    }
}
