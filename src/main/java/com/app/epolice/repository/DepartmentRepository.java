package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findDepartmentsByActive(boolean active);
}
