package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.service.CrimeReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/crime-reports")
public class CrimeReportController {
    /**
     * Initializing the objects
     */
    CrimeReportService crimeReportService;
    public CrimeReportController(CrimeReportService crimeReportService) {
        this.crimeReportService = crimeReportService;
    }

    /**
     * Showing all the crimeReports
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeReports(){
        return crimeReportService.listAllCrimeReports();
    }

    /**
     * Adding the crimeReports
     * @param crimeReport
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCrimeReport(@RequestBody List<CrimeReport> crimeReport){
        return crimeReportService.addNewCrimeReports(crimeReport);
    }

    /**
     * Updating the crimeReports
     * @param crimeReport
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeReport(@RequestBody CrimeReport crimeReport){
        return crimeReportService.updateCrimeReport(crimeReport);
    }

    /**
     * deleting the crimeReports
     * @param crimeReportList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeReport(@RequestBody List<CrimeReport> crimeReportList){
        return crimeReportService.deleteCrimeReport(crimeReportList);
    }
}
