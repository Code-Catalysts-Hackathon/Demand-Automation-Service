package org.automate.demand.ltc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.automate.demand.ltc.entity.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemandDto {
    Long id;
    String demandId;
    BusinessUnitEntity businessUnit;
    PlatformEntity platform;
    LabEntity lab;
    TeamEntity team;
    String job_title;
    Grade grade;
    DepartmentEntity department;
    EmployeeEntity manager;
    LocalDate demandDate;
    DemandStatus status;
    String comments;
    String managerName;
    String primarySkill;
    String SecondarySkill;
    String tertiarySkill;
}
