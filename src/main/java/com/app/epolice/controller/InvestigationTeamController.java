package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.service.InvestigationTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * The type Investigation team controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/investigation-team")
public class InvestigationTeamController {
    private static final Logger LOG = LogManager.getLogger(InvestigationTeamController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * The Investigation team service.
     */
    InvestigationTeamService investigationTeamService;

    /**
     * Instantiates a new Investigation team controller.
     *
     * @param investigationTeamService the investigation team service
     */
    public InvestigationTeamController(InvestigationTeamService investigationTeamService) {
        this.investigationTeamService = investigationTeamService;
    }

    /**
     * Authorizing the token
     *
     * @param token the token
     * @return boolean boolean
     * @author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return InvestigationTeamController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return response entity
     * @author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the investigationTeams
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfInvestigationTeams(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the investigation Teams");
            return investigationTeamService.listAllInvestigationTeams();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the investigationTeams
     *
     * @param token             the token
     * @param investigationTeam the investigation team
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addInvestigationTeam(@RequestHeader("Authorization") String token,@Valid @RequestBody List<InvestigationTeam> investigationTeam){
        if (authorization(token)) {
            LOG.info("Adding the investigation Teams");
            return investigationTeamService.addNewInvestigationTeams(investigationTeam);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the investigationTeams
     *
     * @param token             the token
     * @param investigationTeam the investigation team
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateInvestigationTeam(@RequestHeader("Authorization") String token, @RequestBody InvestigationTeam investigationTeam){
        if (authorization(token)) {
            LOG.info("updating the investigation Teams");
            return investigationTeamService.updateInvestigationTeam(investigationTeam);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the investigationTeams
     *
     * @param token                 the token
     * @param investigationTeamList the investigation team list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteInvestigationTeam(@RequestHeader("Authorization") String token, @RequestBody List<InvestigationTeam> investigationTeamList){
        if (authorization(token)) {
            LOG.info("deleting the investigation Teams");
            return investigationTeamService.deleteInvestigationTeam(investigationTeamList);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Find all investigation teams by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllInvestigationTeamsByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the investigation teams by date");
            return investigationTeamService.findAllInvestigationTeamsByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Showing all the investigationTeams
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list/team")
    public ResponseEntity<Object> findInvestigationTeamById(@RequestHeader("Authorization") String token,@RequestParam Long id) throws ParseException {
        if (authorization(token)) {
            LOG.info("Listing the investigation Team by their id");
            return investigationTeamService.findInvestigationTeamById(id);
        } else {
            return unAuthorizeUser();
        }
    }
}
