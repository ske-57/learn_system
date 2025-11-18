package com.example.learn_system.Services.interfaces;

import com.example.learn_system.dto.OrganizationDto.OrganizationDTO;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDTO> getAllOrganizations();
}
