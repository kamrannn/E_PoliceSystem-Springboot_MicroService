package com.app.epolice.repository;

import com.app.epolice.model.entity.user.Role;
import com.app.epolice.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * The interface Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<Role> findAllByActive(boolean active);

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<Role> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all roles by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_role where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<Role> findAllRolesByDate(Date date);
}
