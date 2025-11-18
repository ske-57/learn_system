package com.example.learn_system.Services;

import com.example.learn_system.Entity.Employee;
import com.example.learn_system.Entity.Organization;
import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Repository.EmployeeRepository;
import com.example.learn_system.Repository.OrganizationRepository;
import com.example.learn_system.Services.interfaces.EmployeeService;
import com.example.learn_system.dto.EmployeeDto.EmployeeCreateDTO;
import com.example.learn_system.dto.EmployeeDto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();


        return list.stream()
                .map(EmployeeServiceImpl::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeCreateDTO req) {
        Long orgId = req.getOrganizationId();

        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("ORGANIZATION", orgId));


        if (validate(req) != null) throw new ValidationException(validate(req));

        Employee employee = toEmployeeEntity(req);

        employee.setOrganization(org);

        return toEmployeeDTO(employeeRepository.save(employee));
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

    private static Employee toEmployeeEntity(EmployeeCreateDTO req) {
        if (req == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(req.getName());
        employee.setLastName(req.getLastName());
        employee.setMiddleName(req.getMiddleName());
        employee.setSnils(req.getSnils());
        employee.setBirthDate(req.getBirthDate());
        employee.setGrade(req.getGrade());
        employee.setPhone(req.getPhone());
        employee.setEmail(req.getEmail());
        employee.setEducation(req.getEducation());
        employee.setIsActive(req.getIsActive() != null ? req.getIsActive() : true);


        return employee;

    }

    private static String validate(EmployeeCreateDTO req) {
        if (req.getName() == null || req.getName().isEmpty()) {
            return "Field name is null or empty";
        }
        if (req.getLastName() == null || req.getLastName().isEmpty()) {
            return "Field lastname is null or empty";
        }
        if (req.getEducation() == null || req.getEducation().isEmpty()) {
            return "Field education is null or empty";
        }
        return null;
    }
}
