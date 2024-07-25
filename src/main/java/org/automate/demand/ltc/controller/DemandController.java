package org.automate.demand.ltc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.automate.demand.ltc.domain.DemandDto;
import org.automate.demand.ltc.domain.DemandResponse;
import org.automate.demand.ltc.service.DemandService;
import org.automate.demand.ltc.service.DemandSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api/demands")
@AllArgsConstructor
@Tag(name = "Demand" ,description = "Demand Management API")
@CrossOrigin("*")
public class DemandController {

    private final DemandSkillService demandSkillService;
    private final DemandService demandService;

    @GetMapping
    public ResponseEntity<DemandResponse> list(){
        List<DemandDto> demandEntities = demandSkillService.findAll();
        DemandResponse demandResponse = DemandResponse.builder()
                .list(demandEntities)
                .totalEntries(demandEntities.size())
                .build();
        return ResponseEntity.ok().body(demandResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandDto> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(demandSkillService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DemandDto> create(@RequestBody DemandDto demandDto){
        return ResponseEntity.ok(demandService.add(demandDto));
    }

    @PutMapping
    public ResponseEntity<DemandDto> update(@RequestBody  DemandDto demandDto){
        return ResponseEntity.ok(demandService.add(demandDto));
    }

}
