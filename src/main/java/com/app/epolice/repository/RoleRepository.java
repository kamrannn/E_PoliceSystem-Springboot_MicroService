package com.app.epolice.repository;

import com.app.epolice.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByActive(boolean active);
}
