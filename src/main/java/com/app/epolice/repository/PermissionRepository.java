package com.app.epolice.repository;

import com.app.epolice.model.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
