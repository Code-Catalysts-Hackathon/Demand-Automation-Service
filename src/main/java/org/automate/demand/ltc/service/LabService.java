package org.automate.demand.ltc.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.automate.demand.ltc.domain.BusinessUnitDto;
import org.automate.demand.ltc.domain.LabDto;
import org.automate.demand.ltc.domain.PlatformDto;
import org.automate.demand.ltc.entity.BusinessUnitEntity;
import org.automate.demand.ltc.entity.LabEntity;
import org.automate.demand.ltc.repository.LabRepository;
import org.automate.demand.ltc.repository.PlatformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class LabService {
    private final ModelMapper modelMapper;
    private final LabRepository labRepository;
    public List<LabDto> getLabsByPlatformId(Long platformId) {
        return labRepository.findByPlatformId(platformId).stream()
                .map(this::convertToDto).collect(Collectors.toList());

       }

    private LabDto convertToDto(LabEntity entity) {
        LabDto dto = new LabDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPlatformId(entity.getPlatform().getId());
        return dto;
    }
}
