package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeType;
import com.app.epolice.service.CrimeTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/crime-type")
public class CrimeTypeController {
    private static final Logger LOG = LogManager.getLogger(CrimeTypeController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Initializing the crime type service object
     */
    CrimeTypeService crimeTypeService;
    public CrimeTypeController(CrimeTypeService crimeTypeService) {
        this.crimeTypeService = crimeTypeService;
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
        return CrimeTypeController.token.equals(token);
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
     * Showing all the crimeTypes
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeTypes(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("listing the crime types");
            return crimeTypeService.listAllCrimeTypes();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the crimeTypes
     * @param crimeType
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCrimeType(@RequestHeader("Authorization") String token, @RequestBody List<CrimeType> crimeType){
        if (authorization(token)) {
            LOG.info("adding the crime types");
            return crimeTypeService.addNewCrimeTypes(crimeType);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the crimeTypes
     * @param crimeType
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeType(@RequestHeader("Authorization") String token, @RequestBody CrimeType crimeType){
        if (authorization(token)) {
            LOG.info("updating the crime types");
            return crimeTypeService.updateCrimeType(crimeType);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the crimeTypes
     * @param crimeTypeList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeType(@RequestHeader("Authorization") String token, @RequestBody List<CrimeType> crimeTypeList){
        if (authorization(token)) {
            LOG.info("updating the crime types");
            return crimeTypeService.deleteCrimeType(crimeTypeList);
        } else {
            return unAuthorizeUser();
        }
    }
}
