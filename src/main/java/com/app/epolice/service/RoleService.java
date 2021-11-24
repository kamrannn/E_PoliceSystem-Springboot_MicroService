package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.user.Role;
import com.app.epolice.repository.RoleRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * The type Role service.
 */
@Service
public class RoleService {
    private static final Logger LOG = LogManager.getLogger(RoleService.class);
    /**
     * The Role repository.
     */
    RoleRepository roleRepository;

    /**
     * Instantiates a new Role service.
     *
     * @param roleRepository the role repository
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Fetching all the roles from the database
     *
     * @return list of roles
     */
    public ResponseEntity<Object> listAllRoles() {
        try {
            List<Role> roleList = roleRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (roleList.isEmpty()) {
                return new ResponseEntity<>("There are no roles in the database", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(roleList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of roles in the database
     *
     * @param roleList adding list of roles
     * @return response entity
     */
    public ResponseEntity<Object> addNewRoles(List<Role> roleList) {
        try {
            if (roleList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.OK);
            } else {
                for (Role role:roleList
                ) {
                    role.setCreatedDate(DateTime.getDateTime());
                    role.setActive(true);
                    roleRepository.save(role);
                }
                if(roleList.size()==1){
                    return new ResponseEntity<>("Role is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Roles are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Roles from the database
     *
     * @param roleList the role list
     * @return response entity
     */
    public ResponseEntity<Object> deleteRole(List<Role> roleList){
        try{
            if(roleList.isEmpty()){
                return new ResponseEntity<>("No Role is selected for the deletion",HttpStatus.OK);
            }else{
                for (Role role:roleList
                ) {
                    role.setActive(false);
                    role.setUpdatedDate(DateTime.getDateTime());
                    roleRepository.save(role);
                }
                if(roleList.size()==1){
                    return new ResponseEntity<>("Role is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Roles are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the role in the database.
     *
     * @param role the role
     * @return response entity
     */
    public ResponseEntity<Object> updateRole(Role role){
        try{
            if(null==role){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.OK);
            }else{
                Optional<Role> roleOptional = roleRepository.findById(role.getId());
                if(roleOptional.isPresent()){
                    role.setUpdatedDate(DateTime.getDateTime());
                    roleRepository.save(role);
                    return new ResponseEntity<>("Role is successfully updated.", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Role doesn't exists in the database.", HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is fetching all the roles for a specific date
     *
     * @param date the date
     * @return response entity
     */
    public ResponseEntity<Object> findRolesByDate(java.sql.Date date) {
        try {
            List<Role> roleList = roleRepository.findAllRolesByDate(date);
            if (roleList.isEmpty()) {
                return new ResponseEntity<>("There are no roles in the database", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(roleList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}