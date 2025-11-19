package com.example.learn_system.Services.interfaces;

import com.example.learn_system.dto.GroupDto.CreateGroupDTO;
import com.example.learn_system.dto.GroupDto.GroupDTO;

import java.util.List;

public interface GroupService {
    List<GroupDTO> getAllGroups();
    GroupDTO createGroup(CreateGroupDTO req);
    void addEmployeeToGroup(Long groupId, Long employeeId);
}
