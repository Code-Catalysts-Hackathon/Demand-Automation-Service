package org.automate.demand.ltc.service;

import lombok.AllArgsConstructor;
import org.automate.demand.ltc.constant.StringConstant;
import org.automate.demand.ltc.domain.Demand;
import org.automate.demand.ltc.domain.DemandDto;
import org.automate.demand.ltc.domain.Student;
import org.automate.demand.ltc.entity.DemandEntity;
import org.automate.demand.ltc.entity.DemandSkillEntity;
import org.automate.demand.ltc.entity.EmployeeEntity;
import org.automate.demand.ltc.repository.DemandSkillRepository;
import org.automate.demand.ltc.util.ErrorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class DemandSkillService {

    private final DemandSkillRepository demandSkillRepository;
    private final ModelMapper modelMapper;

    public List<DemandDto> findAll(){
        List<DemandSkillEntity> demandSkills =  demandSkillRepository.findAll();
        Map<String,List<DemandSkillEntity>> demandSkillTypeMap = demandSkills.stream()
                .collect(Collectors.groupingBy(demandSkillEntity ->
                        demandSkillEntity.getDemand().getDemandId() + "-" +
                                demandSkillEntity.getSkillType(),toList()));
        List<DemandDto> demandSkillsResp= new ArrayList<>();

        for (Map.Entry<String,List<DemandSkillEntity>> demandSkillEntry : demandSkillTypeMap.entrySet()){
            DemandEntity demandEntity = demandSkillEntry.getValue().get(0).getDemand();
            DemandDto demandDto = modelMapper.map(demandEntity, DemandDto.class);
            demandDto.setManagerName(demandDto.getManager().getName());
            demandDto.setPrimarySkill(demandSkillTypeMap.get(demandEntity.getDemandId()+"-"+"primary").get(0).getSkill().getName());
            demandDto.setSecondarySkill(demandSkillTypeMap.get(demandEntity.getDemandId()+"-"+"secondary").get(0).getSkill().getName());
            demandDto.setTertiarySkill(demandSkillTypeMap.get(demandEntity.getDemandId()+"-"+"tertiary").get(0).getSkill().getName());
            demandSkillsResp.add(demandDto);
        }
        return demandSkillsResp;
    }

    public DemandDto findById(Long id){
        Set<DemandSkillEntity> existingDemandSkills = demandSkillRepository.findByDemandId(id).orElseThrow(ErrorUtils.demandNotFoundError());
        DemandEntity demandEntity = existingDemandSkills.stream().findFirst().get().getDemand();
        DemandDto demandDto = modelMapper.map(demandEntity, DemandDto.class);
        demandDto.setManagerName(demandDto.getManager().getName());
        for(DemandSkillEntity demandSkillEntity : existingDemandSkills){
            if (demandSkillEntity.getSkillType().equals(StringConstant.skillTypes.PRIMARY)){
                demandDto.setPrimarySkill(demandSkillEntity.getSkill().getName());
            }else if (demandSkillEntity.getSkillType().equals(StringConstant.skillTypes.SECONDARY)){
                demandDto.setSecondarySkill(demandSkillEntity.getSkill().getName());
            }else {
                demandDto.setTertiarySkill(demandSkillEntity.getSkill().getName());
            }
        }
        return demandDto;
    }

}
