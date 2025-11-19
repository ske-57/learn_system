package com.example.learn_system.Services;

import com.example.learn_system.Entity.Course;
import com.example.learn_system.Entity.Group;
import com.example.learn_system.Exceptions.ConflictException;
import com.example.learn_system.Exceptions.ResourceNotFoundException;
import com.example.learn_system.Exceptions.ValidationException;
import com.example.learn_system.Repository.CourseRepository;
import com.example.learn_system.Repository.EmployeeRepository;
import com.example.learn_system.Repository.GroupRepository;
import com.example.learn_system.Services.interfaces.GroupService;
import com.example.learn_system.dto.GroupDto.CreateGroupDTO;
import com.example.learn_system.dto.GroupDto.GroupDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;

    public GroupServiceImpl(GroupRepository groupRepository, CourseRepository courseRepository, EmployeeRepository employeeRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<GroupDTO> getAllGroups() {
        List<Group> list = groupRepository.findAll();

        return list.stream()
                .map(GroupServiceImpl::toGroupResponse)
                .toList();
    }

    @Override
    public GroupDTO createGroup(CreateGroupDTO req) {
        if (req == null || !validate(req)) return null;
        if (groupRepository.existsById(req.getId()))
            throw new ConflictException("Group with current id already existed");
        Long courseId = req.getCourseId();


        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException("COURSE", courseId));

        Group group = toGroupEntity(req);

        group.setCourse(course);

        return toGroupResponse(groupRepository.save(group));
    }

    @Override
    @Transactional
    public void addEmployeeToGroup(Long groupId, Long employeeId) {
        if (!groupRepository.existsById(groupId)) throw new ResourceNotFoundException("GROUP", groupId);
        if (!employeeRepository.existsById(employeeId)) throw new ResourceNotFoundException("EMPLOYEE", employeeId);

        groupRepository.addEmployeeToGroup(groupId, employeeId);
    }


    private static GroupDTO toGroupResponse(Group g) {
        if (g == null) return null;

        GroupDTO dto = new GroupDTO();

        dto.setId(g.getId());
        dto.setStartDate(g.getStartDate());
        dto.setEndDate(g.getEndDate());

        if (g.getCourse() != null) {
            dto.setCourseId(g.getCourse().getId());
            dto.setCourseName(g.getCourse().getName());
        }

        return dto;
    }

    private static Group toGroupEntity(CreateGroupDTO req) {
        Group group = new Group();
        group.setId(req.getId());
        group.setStartDate(req.getStartDate());
        group.setEndDate(req.getEndDate());

        return group;
    }


    private static boolean validate(CreateGroupDTO req) {
        if (req.getId() == null || req.getId() <= 0)
            throw new ValidationException("Group id is null or <= 0");
        if (req.getCourseId() == null || req.getCourseId() <= 0)
            throw new ValidationException("Course id is null or <= 0");
        return true;
    }
}
