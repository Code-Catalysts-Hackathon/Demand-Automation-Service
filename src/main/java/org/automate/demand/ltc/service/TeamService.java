package org.automate.demand.ltc.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.automate.demand.ltc.domain.BusinessUnitDto;
import org.automate.demand.ltc.domain.LabDto;
import org.automate.demand.ltc.domain.TeamDto;
import org.automate.demand.ltc.entity.BusinessUnitEntity;
import org.automate.demand.ltc.entity.TeamEntity;
import org.automate.demand.ltc.repository.BusinessUnitRepository;
import org.automate.demand.ltc.repository.LabRepository;
import org.automate.demand.ltc.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class TeamService {
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    public List<TeamDto> getTeamsByLabId(Long labId) {
        List<TeamEntity> teamEntities = teamRepository.findByLabId(labId);
        return teamRepository.findByLabId(labId).stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }


    private TeamDto convertToDto(TeamEntity entity) {
        TeamDto dto = new TeamDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLabId(entity.getLab().getId());
        return dto;
    }
}
