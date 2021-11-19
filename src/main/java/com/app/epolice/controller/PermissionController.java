package com.app.epolice.controller;

import com.app.epolice.model.entity.user.Permission;
import com.app.epolice.service.PermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Permission controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/permissions")
public class PermissionController {
    private static final Logger LOG = LogManager.getLogger(PermissionController.class);

    /**
     * The Permission service.
     */
    PermissionService permissionService;

    /**
     * Instantiates a new Permission controller.
     *
     * @param permissionService the permission service
     */
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * Showing all the permissions
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfPermissions() {
        LOG.info("Listing all the permissions");
        return permissionService.listAllPermissions();
    }

    /**
     * Showing all the permissions
     *
     * @return response entity
     */
    @GetMapping("/sorted-list")
    public ResponseEntity<Object> sortedListOfPermissions() {
        LOG.info("Listing all the permissions");
        return permissionService.sortedListOfPermissions();
    }

    /**
     * Adding the permissions
     *
     * @param permissions the permissions
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPermission(@Valid @RequestBody List<Permission> permissions) {
        LOG.info("adding all the permissions");
        return permissionService.addNewPermissions(permissions);
    }

    /**
     * Updating the permissions
     *
     * @param permission the permission
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePermission(@RequestBody Permission permission) {
        LOG.info("updating the permissions");
        return permissionService.updatePermission(permission);
    }

    /**
     * deleting the permissions
     *
     * @param permissionList the permission list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePermission(@RequestBody List<Permission> permissionList) {
        LOG.info("updating the permissions");
        return permissionService.deletePermission(permissionList);

    }

    /**
     * Find permissions by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllPermissionsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the permissions by date");
        return permissionService.findAllPermissionsByDate(date);

    }
}
