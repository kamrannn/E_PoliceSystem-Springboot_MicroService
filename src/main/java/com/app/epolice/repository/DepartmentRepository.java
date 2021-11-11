package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<Department> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all police departments by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_police_department where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<Department> findAllPoliceDepartmentsByDate(Date date);
}