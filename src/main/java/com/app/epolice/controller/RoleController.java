package com.app.epolice.controller;

import com.app.epolice.model.entity.user.Role;
import com.app.epolice.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/roles")
public class RoleController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Showing all the roles
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRoles(){
        return roleService.listAllRoles();
    }

    /**
     * Adding the roles
     * @param roles
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRole(@RequestBody List<Role> roles){
        return roleService.addNewRoles(roles);
    }

    /**
     * Updating the roles
     * @param role
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    /**
     * deleting the roles
     * @param roleList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRole(@RequestBody List<Role> roleList){
        return roleService.deleteRole(roleList);
    }
}
