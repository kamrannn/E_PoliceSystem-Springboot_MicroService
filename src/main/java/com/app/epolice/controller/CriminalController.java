package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.Criminal;
import com.app.epolice.service.CriminalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Criminal controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/criminal")
public class CriminalController {
    private static final Logger LOG = LogManager.getLogger(CriminalController.class);

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
     * Showing all the criminals
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCriminals() {
        LOG.info("Listing all the criminals");
        return criminalService.listAllCriminals();
    }

    /**
     * Adding the criminals
     *
     * @param criminal the criminal
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCriminal(@Valid @RequestBody List<Criminal> criminal) {
        LOG.info("adding the criminals");
        return criminalService.addNewCriminals(criminal);
    }

    /**
     * Updating the criminals
     *
     * @param criminal the criminal
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCriminal(@RequestBody Criminal criminal) {
        LOG.info("updating the criminals");
        return criminalService.updateCriminal(criminal);
    }

    /**
     * deleting the criminals
     *
     * @param criminalList the criminal list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCriminal(@RequestBody List<Criminal> criminalList) {
        LOG.info("deleting the criminals");
        return criminalService.deleteCriminal(criminalList);
    }

    /**
     * find criminals by their cnic
     *
     * @param cnic the cnic
     * @return response entity
     */
    @GetMapping("/find-by-cnic")
    public ResponseEntity<Object> findCriminalByCnic(@RequestHeader String cnic) {
        LOG.info("find the criminals by cnic");
        return criminalService.findCriminalByCnic(cnic);
    }

    /**
     * find criminals by their cnic having return type boolean
     *
     * @param cnic the cnic
     * @return boolean
     */
    @GetMapping("/check-criminal-record")
    public boolean verifyPersonCriminalRecord(@RequestHeader String cnic) {
        LOG.info("Verifying the criminal record by their cnic");
        return criminalService.verifyPersonCriminalRecord(cnic);
    }

    /**
     * Find all criminals by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllCriminalsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the criminals by date");
        return criminalService.findAllCriminalsByDate(date);
    }

    /**
     * Checking whether feign client is working or not
     *
     * @return string
     */
    @GetMapping("/check-currency-record")
    public String checkFeignCurrencyMethod() {
        return criminalService.checkFeignCurrencyMethod();
    }
}
