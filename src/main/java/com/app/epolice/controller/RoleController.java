package com.app.epolice.controller;

import com.app.epolice.model.entity.user.Role;
import com.app.epolice.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

/**
 * The type Role controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/roles")
public class RoleController {
    private static final Logger LOG = LogManager.getLogger(RoleController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

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
     * Authorizing the token
     *
     * @param token the token
     * @return boolean
     * @Author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return RoleController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return response entity
     * @Author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the roles
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRoles(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the roles");
            return roleService.listAllRoles();
        } else {
            return unAuthorizeUser();
        }
    }

    @GetMapping("/by-date")
    public ResponseEntity<Object> findRolesByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the roles by date");
            return roleService.findRolesByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the roles
     *
     * @param token the token
     * @param roles the roles
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRole(@RequestHeader("Authorization") String token, @RequestBody List<Role> roles){
        if (authorization(token)) {
            LOG.info("Adding new roles");
            return roleService.addNewRoles(roles);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the roles
     *
     * @param token the token
     * @param role  the role
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRole(@RequestHeader("Authorization") String token, @RequestBody Role role){
        if (authorization(token)) {
            LOG.info("Updating existing roles");
            return roleService.updateRole(role);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the roles
     *
     * @param token    the token
     * @param roleList the role list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRole(@RequestHeader("Authorization") String token, @RequestBody List<Role> roleList){
        if (authorization(token)) {
            LOG.info("deleting existing roles");
            return roleService.deleteRole(roleList);
        } else {
            return unAuthorizeUser();
        }

    }
}
