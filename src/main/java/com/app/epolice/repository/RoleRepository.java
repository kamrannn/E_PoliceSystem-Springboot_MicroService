package com.app.epolice.repository;

import com.app.epolice.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
