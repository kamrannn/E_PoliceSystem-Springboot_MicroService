package com.app.epolice.controller;

import com.app.epolice.model.entity.user.Permission;
import com.app.epolice.service.PermissionService;
import com.app.epolice.util.ExceptionHandling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.validation.Valid;
import java.util.List;

/**
 * The type Permission controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private static final Logger LOG = LogManager.getLogger(PermissionController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

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
     * Authorizing the token
     *
     * @param token the token
     * @return boolean
     * @author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return PermissionController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return response entity
     * @author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the permissions
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfPermissions(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            LOG.info("Listing all the permissions");
            return permissionService.listAllPermissions();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Showing all the permissions
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/sorted-list")
    public ResponseEntity<Object> sortedListOfPermissions(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            LOG.info("Listing all the permissions");
            return permissionService.sortedListOfPermissions();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the permissions
     *
     * @param token       the token
     * @param permissions the permissions
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPermission(@RequestHeader("Authorization") String token, @Valid @RequestBody List<Permission> permissions) {
        if (authorization(token)) {
            LOG.info("adding all the permissions");
            return permissionService.addNewPermissions(permissions);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the permissions
     *
     * @param token      the token
     * @param permission the permission
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePermission(@RequestHeader("Authorization") String token, @RequestBody Permission permission) {
        if (authorization(token)) {
            LOG.info("updating the permissions");
            return permissionService.updatePermission(permission);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the permissions
     *
     * @param token          the token
     * @param permissionList the permission list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePermission(@RequestHeader("Authorization") String token, @RequestBody List<Permission> permissionList) {
        if (authorization(token)) {
            LOG.info("updating the permissions");
            return permissionService.deletePermission(permissionList);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Find permissions by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllPermissionsByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the permissions by date");
            return permissionService.findAllPermissionsByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ExceptionHandling.handleMethodArgumentNotValid(ex);
    }
}
