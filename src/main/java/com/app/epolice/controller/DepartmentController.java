package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /**
     * Initializing the Objects
     */
    DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Showing all the departments
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfDepartments(){
        return departmentService.listAllDepartments();
    }

    /**
     * Adding the departments
     * @param department
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addDepartment(@RequestBody List<Department> department){
        return departmentService.addNewDepartments(department);
    }

    /**
     * Updating the departments
     * @param department
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateDepartment(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    /**
     * deleting the departments
     * @param departmentList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDepartment(@RequestBody List<Department> departmentList){
        return departmentService.deleteDepartment(departmentList);
    }
}
