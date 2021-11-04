package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.service.InvestigationTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/investigation-team")
public class InvestigationTeamController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    InvestigationTeamService investigationTeamService;

    public InvestigationTeamController(InvestigationTeamService investigationTeamService) {
        this.investigationTeamService = investigationTeamService;
    }

    /**
     * Showing all the investigationTeams
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfInvestigationTeams(){
        return investigationTeamService.listAllInvestigationTeams();
    }

    /**
     * Adding the investigationTeams
     * @param investigationTeam
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addInvestigationTeam(@RequestBody List<InvestigationTeam> investigationTeam){
        return investigationTeamService.addNewInvestigationTeams(investigationTeam);
    }

    /**
     * Updating the investigationTeams
     * @param investigationTeam
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateInvestigationTeam(@RequestBody InvestigationTeam investigationTeam){
        return investigationTeamService.updateInvestigationTeam(investigationTeam);
    }

    /**
     * deleting the investigationTeams
     * @param investigationTeamList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteInvestigationTeam(@RequestBody List<InvestigationTeam> investigationTeamList){
        return investigationTeamService.deleteInvestigationTeam(investigationTeamList);
    }
}
