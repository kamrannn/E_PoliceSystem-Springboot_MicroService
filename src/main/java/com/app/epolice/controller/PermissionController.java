package com.app.epolice.controller;

import com.app.epolice.model.entity.user.Permission;
import com.app.epolice.service.PermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * Showing all the permissions
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfPermissions(){
        return permissionService.listAllPermissions();
    }

    /**
     * Adding the permissions
     * @param permissions
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPermission(@RequestBody List<Permission> permissions){
        return permissionService.addNewPermissions(permissions);
    }

    /**
     * Updating the permissions
     * @param permission
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePermission(@RequestBody Permission permission){
        return permissionService.updatePermission(permission);
    }

    /**
     * deleting the permissions
     * @param permissionList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePermission(@RequestBody List<Permission> permissionList){
        return permissionService.deletePermission(permissionList);
    }
}
