package com.example.learn_system.Repository;

import com.example.learn_system.Entity.Employee;
import com.example.learn_system.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Modifying
    @Query(nativeQuery = true,
            value = """
                    INSERT INTO group_members (group_id, employee_id) VALUES (?1, ?2);
                    """)
    void addEmployeeToGroup(Long groupId, Long employeeId);
}
