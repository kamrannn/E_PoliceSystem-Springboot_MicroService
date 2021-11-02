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

@Service
public class RoleService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Fetching all the roles from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllRoles() {
        try {
            List<Role> roleList = roleRepository.findAllByActive(true);
            if (roleList.isEmpty()) {
                return new ResponseEntity<>("There are no roles in the database", HttpStatus.NOT_FOUND);
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
     * @param roleList
     * @return
     */
    public ResponseEntity<Object> addNewRoles(List<Role> roleList) {
        try {
            if (roleList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
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
     * @param roleList
     * @return
     */
    public ResponseEntity<Object> deleteRole(List<Role> roleList){
        try{
            if(roleList.isEmpty()){
                return new ResponseEntity<>("No Role is selected for the deletion",HttpStatus.BAD_REQUEST);
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
     * @param role
     * @return
     */
    public ResponseEntity<Object> updateRole(Role role){
        try{
            if(null==role){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                role.setUpdatedDate(DateTime.getDateTime());
                roleRepository.save(role);
                return new ResponseEntity<>("Role is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
