package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private static final Logger LOG = LogManager.getLogger(DepartmentController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Initializing the Objects
     */
    DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Authorizing the token
     *
     * @param token
     * @return
     * @Author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return DepartmentController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return
     * @Author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the departments
     * @return
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
     * @param department
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addDepartment(@RequestHeader("Authorization") String token, @RequestBody List<Department> department){
        return departmentService.addNewDepartments(department);
    }

    /**
     * Updating the departments
     * @param department
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateDepartment(@RequestHeader("Authorization") String token, @RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    /**
     * deleting the departments
     * @param departmentList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDepartment(@RequestHeader("Authorization") String token, @RequestBody List<Department> departmentList){
        return departmentService.deleteDepartment(departmentList);
    }
}
