package com.app.epolice.service;

import com.app.epolice.model.entity.policestation.Department;
import com.app.epolice.repository.DepartmentRepository;
import com.app.epolice.util.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Fetching all the police stations from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllDepartments() {
        try {
            List<Department> departmentList = departmentRepository.findDepartmentsByActive(true);
            if (departmentList.isEmpty()) {
                return new ResponseEntity<>("There are no departments in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(departmentList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of police stations in the database
     * @param departmentList
     * @return
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Departments from the database
     * @param departmentList
     * @return
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the police station in the database.
     * @param department
     * @return
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
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
