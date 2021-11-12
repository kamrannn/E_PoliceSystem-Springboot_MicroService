package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.user.Permission;
import com.app.epolice.repository.PermissionRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * The type Permission service.
 */
@Service
public class PermissionService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /**
     * The Permission repository.
     */
    PermissionRepository permissionRepository;

    /**
     * Instantiates a new Permission service.
     *
     * @param permissionRepository the permission repository
     */
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    /**
     * Fetching all the permissions from the database
     *
     * @return response entity
     */
    public ResponseEntity<Object> listAllPermissions() {
        try {
            List<Permission> permissionList = permissionRepository.findAllByActive(true);
            if (permissionList.isEmpty()) {
                return new ResponseEntity<>("There are no permissions in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(permissionList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Fetching all the permissions from the database
     *
     * @return response entity
     */
    public ResponseEntity<Object> sortedListOfPermissions() {
        try {
            List<Permission> permissionList = permissionRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (permissionList.isEmpty()) {
                return new ResponseEntity<>("There are no permissions in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(permissionList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of permissions in the database
     *
     * @param permissionList the permission list
     * @return response entity
     */
    public ResponseEntity<Object> addNewPermissions(List<Permission> permissionList) {
        try {
            if (permissionList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (Permission permission:permissionList
                ) {
//                    if(permission.getName().trim().isEmpty()){
//                        return new ResponseEntity<>("You are entering an empty name in the list",HttpStatus.BAD_REQUEST);
//                    }else{
                        permission.setCreatedDate(DateTime.getDateTime());
                        permission.setActive(true);
                        permissionRepository.save(permission);
//                    }
                }
                if(permissionList.size()==1){
                    return new ResponseEntity<>("Permission is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Permissions are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Permissions from the database
     *
     * @param permissionList the permission list
     * @return response entity
     */
    public ResponseEntity<Object> deletePermission(List<Permission> permissionList){
        try{
            if(permissionList.isEmpty()){
                return new ResponseEntity<>("No Permission is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (Permission permission:permissionList
                ) {
                    permission.setActive(false);
                    permission.setUpdatedDate(DateTime.getDateTime());
                    permissionRepository.save(permission);
                }
                if(permissionList.size()==1){
                    return new ResponseEntity<>("Permission is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Permissions are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the permission in the database.
     *
     * @param permission the permission
     * @return response entity
     */
    public ResponseEntity<Object> updatePermission(Permission permission){
        try{
            if(null==permission){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                permission.setUpdatedDate(DateTime.getDateTime());
                permissionRepository.save(permission);
                return new ResponseEntity<>("Permission is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find police stations by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    public ResponseEntity<Object> findAllPermissionsByDate(java.sql.Date date) {
        try {
            List<Permission> permissionList = permissionRepository.findAllPermissionsByDate(date);
            if (permissionList.isEmpty()) {
                return new ResponseEntity<>("There are no police stations in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(permissionList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
