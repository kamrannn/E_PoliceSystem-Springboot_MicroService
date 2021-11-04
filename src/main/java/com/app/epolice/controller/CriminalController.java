package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.Criminal;
import com.app.epolice.service.CriminalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/criminal")
public class CriminalController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    CriminalService criminalService;

    public CriminalController(CriminalService criminalService) {
        this.criminalService = criminalService;
    }

    /**
     * Showing all the criminals
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCriminals(){
        return criminalService.listAllCriminals();
    }

    /**
     * Adding the criminals
     * @param criminal
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCriminal(@RequestBody List<Criminal> criminal){
        return criminalService.addNewCriminals(criminal);
    }

    /**
     * Updating the criminals
     * @param criminal
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCriminal(@RequestBody Criminal criminal){
        return criminalService.updateCriminal(criminal);
    }

    /**
     * deleting the criminals
     * @param criminalList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCriminal(@RequestBody List<Criminal> criminalList){
        return criminalService.deleteCriminal(criminalList);
    }

    /**
     * find criminals by their cnic
     * @param cnic
     * @return
     */
    @GetMapping("/find-by-cnic")
    public ResponseEntity<Object> findCriminalByCnic(@RequestHeader String cnic){
        return criminalService.findCriminalByCnic(cnic);
    }
    /**
     * find criminals by their cnic having return type boolean
     * @param cnic
     * @return
     */
    @GetMapping("/check-criminal-record")
    public boolean verifyPersonCriminalRecord(@RequestHeader String cnic){
        return criminalService.verifyPersonCriminalRecord(cnic);
    }

    /**
     * Checking whether feign client is working or not
     * @return
     */
    @GetMapping("/check-currency-record")
    public String checkFeignCurrencyMethod(){
        return criminalService.checkFeignCurrencyMethod();
    }
}
