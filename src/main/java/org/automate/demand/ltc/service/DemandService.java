package org.automate.demand.ltc.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.automate.demand.ltc.domain.Demand;
import org.automate.demand.ltc.domain.DemandDto;
import org.automate.demand.ltc.entity.DemandEntity;
import org.automate.demand.ltc.entity.DemandSkillEntity;
import org.automate.demand.ltc.entity.DemandSkillKey;
import org.automate.demand.ltc.entity.SkillEntity;
import org.automate.demand.ltc.repository.DemandRepository;
import org.automate.demand.ltc.repository.DemandSkillRepository;
import org.automate.demand.ltc.repository.SkillRepository;
import org.automate.demand.ltc.util.ErrorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DemandService {
    private final DemandRepository demandRepository;
    private final DemandSkillRepository demandSkillRepository;
    private final SkillRepository skillRepository;

    private final ModelMapper modelMapper;

    public List<Demand> findAll() {
        return demandRepository.findAll().stream()
                .map(demandEntity -> modelMapper.map(demandEntity, Demand.class)).collect(Collectors.toList());
    }

    public Demand findById(Long id){
        DemandEntity existingDemand = demandRepository.findById(id).orElseThrow();
        return modelMapper.map(existingDemand, Demand.class);
    }

    @Transactional
    public DemandDto add(DemandDto demandDto){
        Set<DemandSkillEntity> demandSkillEntities;
        DemandEntity demandEntity;
        DemandEntity existingDemandEntity =  demandRepository.findByDemandId(demandDto.getDemandId());
        if (existingDemandEntity != null){
            existingDemandEntity = modelMapper.map(demandDto, DemandEntity.class);
            demandEntity = demandRepository.save(existingDemandEntity);
        }else {
            demandEntity =  modelMapper.map(demandDto, DemandEntity.class);
            demandEntity = demandRepository.save(demandEntity);
        }
        SkillEntity primarySkill = skillRepository.findByName(demandDto.getPrimarySkill());
        SkillEntity secondarySkill = skillRepository.findByName(demandDto.getSecondarySkill());
        SkillEntity tertiarySkill = skillRepository.findByName(demandDto.getTertiarySkill());
        DemandSkillEntity primaryDemandSkill = getDemandSkillObject(demandEntity,primarySkill,"primary");
        DemandSkillEntity secondaryDemandSkill = getDemandSkillObject(demandEntity,secondarySkill,"secondary");
        DemandSkillEntity tertiaryDemandSkill = getDemandSkillObject(demandEntity,tertiarySkill,"tertiary");
        DemandDto response =  modelMapper.map(demandEntity, DemandDto.class);
        response.setManagerName(demandDto.getManager().getName());
        response.setPrimarySkill(primaryDemandSkill.getSkill().getName());
        response.setSecondarySkill(secondaryDemandSkill.getSkill().getName());
        response.setTertiarySkill(tertiaryDemandSkill.getSkill().getName());
        return response;
    }

    private DemandSkillEntity getDemandSkillObject(DemandEntity demandEntity, SkillEntity skill,String skillType){
        DemandSkillEntity demandSkillEntity = new DemandSkillEntity();
        DemandSkillKey demandSkillKey = new DemandSkillKey();
        demandSkillKey.setDemandId(demandEntity.getId());
        demandSkillKey.setSkillId(skill.getId());
        demandSkillEntity.setId(demandSkillKey);
        demandSkillEntity.setSkill(skill);
        demandSkillEntity.setDemand(demandEntity);
        demandSkillEntity.setSkillType(skillType);
        return demandSkillEntity;
    }

    public Demand update(Demand demand){
        DemandEntity existingDemand = demandRepository.findById(demand.id()).orElseThrow(ErrorUtils.demandNotFoundError());
        existingDemand.setBusinessUnit(demand.businessUnit());
        //other Updates
        return modelMapper.map(existingDemand, Demand.class);
    }


}
