package com.app.epolice.controller;

import com.app.epolice.model.entity.user.Role;
import com.app.epolice.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Role controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/roles")
public class RoleController {
    private static final Logger LOG = LogManager.getLogger(RoleController.class);

    /**
     * The Role service.
     */
    RoleService roleService;

    /**
     * Instantiates a new Role controller.
     *
     * @param roleService the role service
     */
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Showing all the roles
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRoles() {
        LOG.info("Listing all the roles");
        return roleService.listAllRoles();
    }

    @GetMapping("/by-date")
    public ResponseEntity<Object> findRolesByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the roles by date");
        return roleService.findRolesByDate(date);
    }

    /**
     * Adding the roles
     *
     * @param roles the roles
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRole(@Valid @RequestBody List<Role> roles) {
        LOG.info("Adding new roles");
        return roleService.addNewRoles(roles);
    }

    /**
     * Updating the roles
     *
     * @param role the role
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRole(@RequestBody Role role) {
        LOG.info("Updating existing roles");
        return roleService.updateRole(role);
    }

    /**
     * deleting the roles
     *
     * @param roleList the role list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRole(@RequestBody List<Role> roleList) {
        LOG.info("deleting existing roles");
        return roleService.deleteRole(roleList);
    }
}
