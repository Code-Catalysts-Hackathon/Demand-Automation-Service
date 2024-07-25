package org.automate.demand.ltc.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.automate.demand.ltc.domain.BusinessUnitDto;
import org.automate.demand.ltc.domain.LabDto;
import org.automate.demand.ltc.domain.PlatformDto;
import org.automate.demand.ltc.entity.LabEntity;
import org.automate.demand.ltc.entity.PlatformEntity;
import org.automate.demand.ltc.repository.PlatformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class PlatformService {
    private final ModelMapper modelMapper;
    private final PlatformRepository platformRepository;
    public List<PlatformDto> getPlatformsByBusinessUnitId(Long businessUnitId) {
        return platformRepository.findByBusinessUnitId(businessUnitId).stream()
                .map(this::convertToDto).collect(Collectors.toList());    }

    private PlatformDto convertToDto(PlatformEntity entity) {
        PlatformDto dto = new PlatformDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBuId(entity.getBusinessUnit().getId());
        return dto;
    }
}
