package com.example.learn_system.Services;

import com.example.learn_system.Entity.Group;
import com.example.learn_system.Repository.GroupRepository;
import com.example.learn_system.Services.interfaces.GroupService;
import com.example.learn_system.dto.GroupDto.GroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    private static GroupDTO toResponse(Group g) {
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

    @Override
    public List<GroupDTO> getAllGroups() {
        List<Group> list = groupRepository.findAll();

        return list.stream()
                .map(GroupServiceImpl::toResponse)
                .toList();
    }
}
