package com.example.learn_system.Services;

import com.example.learn_system.Entity.Organization;
import com.example.learn_system.Repository.OrganizationRepository;
import com.example.learn_system.Services.interfaces.OrganizationService;
import com.example.learn_system.dto.OrganizationDto.OrganizationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    private static OrganizationDTO toResponse(Organization o) {
        if (o == null) return null;

        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(o.getId());
        dto.setName(o.getName());

        return dto;
    }

    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> list = organizationRepository.findAll();

        return list.stream()
                .map(OrganizationServiceImpl::toResponse)
                .collect(Collectors.toList());
    }
}
