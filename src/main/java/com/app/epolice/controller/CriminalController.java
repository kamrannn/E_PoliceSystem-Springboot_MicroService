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

@EnableSwagger2
@RestController
@RequestMapping("/criminal")
public class CriminalController {
    private static final Logger LOG = LogManager.getLogger(CriminalController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    CriminalService criminalService;
    public CriminalController(CriminalService criminalService) {
        this.criminalService = criminalService;
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
        return CriminalController.token.equals(token);
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
     * Showing all the criminals
     * @return
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
     * @param criminal
     * @return
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
     * @param criminal
     * @return
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
     * @param criminalList
     * @return
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
     * @param cnic
     * @return
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
     * @param cnic
     * @return
     */
    @GetMapping("/check-criminal-record")
    public boolean verifyPersonCriminalRecord(@RequestHeader("Authorization") String token, @RequestHeader String cnic){
        return criminalService.verifyPersonCriminalRecord(cnic);
    }

    /**
     * Checking whether feign client is working or not
     * @return
     */
    @GetMapping("/check-currency-record")
    public String checkFeignCurrencyMethod(@RequestHeader("Authorization") String token){
        return criminalService.checkFeignCurrencyMethod();
    }
}
