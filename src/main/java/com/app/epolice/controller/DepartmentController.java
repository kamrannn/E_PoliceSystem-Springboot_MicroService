package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
     * Showing all the departments
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfDepartments() {
        LOG.info("Listing all the departments");
        return departmentService.listAllDepartments();
    }

    /**
     * Adding the departments
     *
     * @param department the department
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addDepartment(@Valid @RequestBody List<Department> department) {
        LOG.info("adding the departments");
        return departmentService.addNewDepartments(department);
    }

    /**
     * Updating the departments
     *
     * @param department the department
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateDepartment(@RequestBody Department department) {
        LOG.info("updating the departments");
        return departmentService.updateDepartment(department);
    }

    /**
     * deleting the departments
     *
     * @param departmentList the department list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDepartment(@RequestBody List<Department> departmentList) {
        LOG.info("deleting the departments");
        return departmentService.deleteDepartment(departmentList);

    }

    /**
     * Find all departments by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllDepartmentsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the departments by date");
        return departmentService.findAllDepartmentsByDate(date);
    }
}
