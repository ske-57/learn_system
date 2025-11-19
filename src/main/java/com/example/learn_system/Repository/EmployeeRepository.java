package com.example.learn_system.Repository;

import com.example.learn_system.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true,
            value = """
                    SELECT e.* from group_members gm
                                        JOIN employees e ON e.id = gm.employee_id
                                        WHERE gm.group_id = ?1;
                    """)
    List<Employee> getEmployeesByGroupId(Long groupId);
}
