package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.service.CrimeReportService;
import com.app.epolice.util.FileUpload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
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
    @PostMapping("/add_multiple_reports")
    public ResponseEntity<Object> addListOfCrimeReports(@RequestBody List<CrimeReport> crimeReport){
        return crimeReportService.addMultipleCrimeReports(crimeReport);
    }

    /**
     * Adding a single report with multiple pictures
     * @param report
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload_singe_report")
    public ResponseEntity<Object> uploadReport( CrimeReport report,@RequestParam("files") MultipartFile[] file) throws IOException {
        return crimeReportService.addSingleCrimeReport(report,file);
    }

    /**
     * Upload a file controller (Testing)
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String reportPictureName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "F:\\Development\\E-Police Project\\Images\\" + 1;
        FileUpload.saveFile(uploadDir,reportPictureName, file);
        return null;
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
