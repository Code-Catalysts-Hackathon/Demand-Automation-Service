package org.automate.demand.ltc.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.automate.demand.ltc.constant.StringConstant;
import org.automate.demand.ltc.domain.*;
import org.automate.demand.ltc.service.BusinessUnitService;
import org.automate.demand.ltc.service.LabService;
import org.automate.demand.ltc.service.PlatformService;
import org.automate.demand.ltc.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api/")
@AllArgsConstructor
@CrossOrigin("*")
public class BusinessEntitiesController {

    private final BusinessUnitService businessUnitService;
    private final PlatformService platformService;
    private final LabService labService;
    private final TeamService teamService;

    @GetMapping("/business-units/")
    public ResponseEntity<ApiResponse> businessUnitList(){
        List<BusinessUnitDto> businessUnits = businessUnitService.getAllBusinessUnits();
        ApiResponse response = ApiResponse.builder()
                .message(StringConstant.SUCCESS)
                .data(businessUnits)
                .build();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/platforms/{buid}")
    public ResponseEntity<ApiResponse> getPlatformsByBu(@PathVariable("buid") Long buId){
        List<PlatformDto> platforms = platformService.getPlatformsByBusinessUnitId(buId);
        ApiResponse response = ApiResponse.builder()
                .message(StringConstant.SUCCESS)
                .data(platforms)
                .build();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/labs/{platformid}")
    public ResponseEntity<ApiResponse> getLabsByPlatform(@PathVariable("platformid") Long platfromId){
        List<LabDto> labs = labService.getLabsByPlatformId(platfromId);
        ApiResponse response = ApiResponse.builder()
                .message(StringConstant.SUCCESS)
                .data(labs)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/teams/{labid}")
    public ResponseEntity<ApiResponse> getTeamsByLabs(@PathVariable("labid") Long labid){
        try{
            List<TeamDto> teams = teamService.getTeamsByLabId(labid);
            ApiResponse response = ApiResponse.builder()
                    .message(StringConstant.SUCCESS)
                    .data(teams)
                    .build();
            return ResponseEntity.ok().body(response);
        }catch(Exception e) {
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message(e.getMessage())
                    .build());
        }


    }

}



