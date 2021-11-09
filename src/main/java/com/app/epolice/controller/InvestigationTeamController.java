package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.service.InvestigationTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/investigation-team")
public class InvestigationTeamController {
    private static final Logger LOG = LogManager.getLogger(InvestigationTeamController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    InvestigationTeamService investigationTeamService;
    public InvestigationTeamController(InvestigationTeamService investigationTeamService) {
        this.investigationTeamService = investigationTeamService;
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
        return InvestigationTeamController.token.equals(token);
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
     * Showing all the investigationTeams
     * @return
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
     * @param investigationTeam
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addInvestigationTeam(@RequestHeader("Authorization") String token, @RequestBody List<InvestigationTeam> investigationTeam){
        if (authorization(token)) {
            LOG.info("Adding the investigation Teams");
            return investigationTeamService.addNewInvestigationTeams(investigationTeam);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the investigationTeams
     * @param investigationTeam
     * @return
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
     * @param investigationTeamList
     * @return
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
}
