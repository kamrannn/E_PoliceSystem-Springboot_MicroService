package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Department repository.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    /**
     * Find departments by active list.
     *
     * @param active the active
     * @return the list
     */
    List<Department> findDepartmentsByActive(boolean active);
}