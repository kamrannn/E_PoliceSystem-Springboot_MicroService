package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.Criminal;
import com.app.epolice.service.CriminalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

/**
 * The type Criminal controller.
 */
@EnableSwagger2
@RestController
@RequestMapping("/criminal")
public class CriminalController {
    private static final Logger LOG = LogManager.getLogger(CriminalController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * The Criminal service.
     */
    CriminalService criminalService;

    /**
     * Instantiates a new Criminal controller.
     *
     * @param criminalService the criminal service
     */
    public CriminalController(CriminalService criminalService) {
        this.criminalService = criminalService;
    }

    /**
     * Authorizing the token
     *
     * @param token the token
     * @return boolean
     * @Author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return CriminalController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return response entity
     * @Author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the criminals
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCriminals(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the criminals");
            return criminalService.listAllCriminals();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the criminals
     *
     * @param token    the token
     * @param criminal the criminal
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCriminal(@RequestHeader("Authorization") String token, @RequestBody List<Criminal> criminal){
        if (authorization(token)) {
            LOG.info("adding the criminals");
            return criminalService.addNewCriminals(criminal);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the criminals
     *
     * @param token    the token
     * @param criminal the criminal
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCriminal(@RequestHeader("Authorization") String token, @RequestBody Criminal criminal){
        if (authorization(token)) {
            LOG.info("updating the criminals");
            return criminalService.updateCriminal(criminal);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the criminals
     *
     * @param token        the token
     * @param criminalList the criminal list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCriminal(@RequestHeader("Authorization") String token, @RequestBody List<Criminal> criminalList){
        if (authorization(token)) {
            LOG.info("deleting the criminals");
            return criminalService.deleteCriminal(criminalList);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * find criminals by their cnic
     *
     * @param token the token
     * @param cnic  the cnic
     * @return response entity
     */
    @GetMapping("/find-by-cnic")
    public ResponseEntity<Object> findCriminalByCnic(@RequestHeader("Authorization") String token, @RequestHeader String cnic){
        if (authorization(token)) {
            LOG.info("find the criminals by cnic");
            return criminalService.findCriminalByCnic(cnic);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * find criminals by their cnic having return type boolean
     *
     * @param token the token
     * @param cnic  the cnic
     * @return boolean
     */
    @GetMapping("/check-criminal-record")
    public boolean verifyPersonCriminalRecord(@RequestHeader("Authorization") String token, @RequestHeader String cnic){
        return criminalService.verifyPersonCriminalRecord(cnic);
    }

    /**
     * Find all criminals by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllCriminalsByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the criminals by date");
            return criminalService.findAllCriminalsByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Checking whether feign client is working or not
     *
     * @param token the token
     * @return string
     */
    @GetMapping("/check-currency-record")
    public String checkFeignCurrencyMethod(@RequestHeader("Authorization") String token){
        return criminalService.checkFeignCurrencyMethod();
    }
}
