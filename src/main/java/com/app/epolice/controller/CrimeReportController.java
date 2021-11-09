package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.service.CrimeReportService;
import com.app.epolice.util.FileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/crime-reports")
public class CrimeReportController {
    private static final Logger LOG = LogManager.getLogger(CrimeReportController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";


    /**
     * Initializing the objects
     */
    CrimeReportService crimeReportService;
    public CrimeReportController(CrimeReportService crimeReportService) {
        this.crimeReportService = crimeReportService;
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
        return CrimeReportController.token.equals(token);
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
     * Showing all the crimeReports
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeReports(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("listing all the crime reports");
            return crimeReportService.listAllCrimeReports();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the crimeReports
     * @param crimeReport
     * @return
     */
    @PostMapping("/add_multiple_reports")
    public ResponseEntity<Object> addListOfCrimeReports(@RequestHeader("Authorization") String token, @RequestBody List<CrimeReport> crimeReport){
        if (authorization(token)) {
            LOG.info("adding the crime reports");
            return crimeReportService.addMultipleCrimeReports(crimeReport);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding a single report with multiple pictures
     * @param report
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload_singe_report")
    public ResponseEntity<Object> uploadReport(@RequestHeader("Authorization") String token, CrimeReport report,@RequestParam("files") MultipartFile[] file) throws IOException {
        if (authorization(token)) {
            LOG.info("adding single crime report");
            return crimeReportService.addSingleCrimeReport(report,file);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Upload a file controller (Testing)
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestHeader("Authorization") String token, @RequestParam("file") MultipartFile file) throws IOException {
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
    public ResponseEntity<Object> updateCrimeReport(@RequestHeader("Authorization") String token, @RequestBody CrimeReport crimeReport){
        if (authorization(token)) {
            LOG.info("updating single crime report");
            return crimeReportService.updateCrimeReport(crimeReport);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the crimeReports
     * @param crimeReportList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeReport(@RequestHeader("Authorization") String token, @RequestBody List<CrimeReport> crimeReportList){
        if (authorization(token)) {
            LOG.info("deleting multiple crime reports");
            return crimeReportService.deleteCrimeReport(crimeReportList);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * verifying the crimeReports
     * @param crimeReportId
     * @return
     */
    @PostMapping("/verify")
    public ResponseEntity<Object> verifyCrimeReport(@RequestHeader("Authorization") String token, @RequestHeader String status,@RequestHeader long crimeReportId,@RequestHeader long policeStationId) throws ParseException {
        if (authorization(token)) {
            LOG.info("verifying and assigning crime reports to a specific police station");
            return crimeReportService.verifyReport(status,crimeReportId,policeStationId);
        } else {
            return unAuthorizeUser();
        }
    }
}
