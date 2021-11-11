package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.repository.DepartmentRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Department service.
 */
@Service
public class DepartmentService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /**
     * The Department repository.
     */
    DepartmentRepository departmentRepository;

    /**
     * Instantiates a new Department service.
     *
     * @param departmentRepository the department repository
     */
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Fetching all the police stations from the database
     *
     * @return response entity
     */
    public ResponseEntity<Object> listAllDepartments() {
        try {
            List<Department> departmentList = departmentRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (departmentList.isEmpty()) {
                return new ResponseEntity<>("There are no departments in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(departmentList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of police stations in the database
     *
     * @param departmentList the department list
     * @return response entity
     */
    public ResponseEntity<Object> addNewDepartments(List<Department> departmentList) {
        try {
            if (departmentList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (Department department:departmentList
                ) {
                    department.setCreatedDate(DateTime.getDateTime());
                    department.setActive(true);
                    departmentRepository.save(department);
                }
                if(departmentList.size()==1){
                    return new ResponseEntity<>("Department is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Departments are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Departments from the database
     *
     * @param departmentList the department list
     * @return response entity
     */
    public ResponseEntity<Object> deleteDepartment(List<Department> departmentList){
        try{
            if(departmentList.isEmpty()){
                return new ResponseEntity<>("No Department is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (Department department:departmentList
                ) {
                    department.setActive(false);
                    department.setUpdatedDate(DateTime.getDateTime());
                    departmentRepository.save(department);
                }
                if(departmentList.size()==1){
                    return new ResponseEntity<>("Department is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Departments are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the police station in the database.
     *
     * @param department the department
     * @return response entity
     */
    public ResponseEntity<Object> updateDepartment(Department department){
        try{
            if(null==department){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                department.setUpdatedDate(DateTime.getDateTime());
                departmentRepository.save(department);
                return new ResponseEntity<>("Department is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all departments by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    public ResponseEntity<Object> findAllDepartmentsByDate(java.sql.Date date) {
        try {
            List<Department> departmentList = departmentRepository.findAllPoliceDepartmentsByDate(date);
            if (departmentList.isEmpty()) {
                return new ResponseEntity<>("There are no departments in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(departmentList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
