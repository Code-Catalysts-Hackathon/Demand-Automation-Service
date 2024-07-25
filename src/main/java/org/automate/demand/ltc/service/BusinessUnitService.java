package org.automate.demand.ltc.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.automate.demand.ltc.domain.BusinessUnitDto;
import org.automate.demand.ltc.entity.BusinessUnitEntity;
import org.automate.demand.ltc.repository.BusinessUnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class BusinessUnitService {
    private final BusinessUnitRepository businessUnitRepository;
    private final ModelMapper modelMapper;

    public List<BusinessUnitDto> getAllBusinessUnits() {
        return businessUnitRepository.findAll().stream()
                .map(this::convertToDto).collect(Collectors.toList());    }


    private BusinessUnitDto convertToDto(BusinessUnitEntity entity) {
        BusinessUnitDto dto = new BusinessUnitDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortCode(entity.getShortCode());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
