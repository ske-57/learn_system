package com.example.learn_system.Services;

import com.example.learn_system.Entity.Employee;
import com.example.learn_system.Repository.EmployeeRepository;
import com.example.learn_system.Services.interfaces.EmployeeService;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import com.example.learn_system.dto.EmployeeDto.SimpleEmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private static EmployeeDTO toEmployeeDTO(Employee e) {
        if (e == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setLastName(e.getLastName());
        dto.setMiddleName(e.getMiddleName());
        dto.setSnils(e.getSnils());
        dto.setBirthDate(e.getBirthDate());
        dto.setGrade(e.getGrade());
        dto.setPhone(e.getPhone());
        dto.setEmail(e.getEmail());
        dto.setEducation(e.getEducation());
        dto.setIsActive(e.getIsActive());

        if (e.getOrganization() != null) {
            dto.setOrganizationId(e.getOrganization().getId());
            dto.setOrganizationName(e.getOrganization().getName());
        }

        return dto;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();


        return list.stream()
                .map(EmployeeServiceImpl::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
