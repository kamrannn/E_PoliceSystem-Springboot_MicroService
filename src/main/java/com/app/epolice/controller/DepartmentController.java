package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Department controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/departments")
public class DepartmentController {
    private static final Logger LOG = LogManager.getLogger(DepartmentController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Initializing the Objects
     */
    DepartmentService departmentService;

    /**
     * Instantiates a new Department controller.
     *
     * @param departmentService the department service
     */
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Authorizing the token
     *
     * @param token the token
     * @return boolean boolean
     * @author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return DepartmentController.token.equals(token);
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
     * Showing all the departments
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfDepartments(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the departments");
            return departmentService.listAllDepartments();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the departments
     *
     * @param token      the token
     * @param department the department
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addDepartment(@RequestHeader("Authorization") String token,@Valid @RequestBody List<Department> department){
        if (authorization(token)) {
            LOG.info("adding the departments");
            return departmentService.addNewDepartments(department);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the departments
     *
     * @param token      the token
     * @param department the department
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateDepartment(@RequestHeader("Authorization") String token, @RequestBody Department department){
        if (authorization(token)) {
            LOG.info("updating the departments");
            return departmentService.updateDepartment(department);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the departments
     *
     * @param token          the token
     * @param departmentList the department list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDepartment(@RequestHeader("Authorization") String token, @RequestBody List<Department> departmentList){
        if (authorization(token)) {
            LOG.info("deleting the departments");
            return departmentService.deleteDepartment(departmentList);
        } else {
            return unAuthorizeUser();
        }

    }

    /**
     * Find all departments by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllDepartmentsByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the departments by date");
            return departmentService.findAllDepartmentsByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }
}
