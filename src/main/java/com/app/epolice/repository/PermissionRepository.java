package com.app.epolice.repository;

import com.app.epolice.model.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

/**
 * The interface Permission repository.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<Permission> findAllByActive(boolean active);

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<Permission> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all permissions by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_permission where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<Permission> findAllPermissionsByDate(Date date);
}
