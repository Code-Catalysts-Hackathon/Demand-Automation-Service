package org.automate.demand.ltc.domain;

import org.automate.demand.ltc.entity.*;

import java.time.LocalDate;

public record Demand(
        Long id,
        String demandId,
        BusinessUnitEntity businessUnit,
        PlatformEntity platform,
        LabEntity lab,
        TeamEntity team,
        String job_title,
        Grade grade,
        DepartmentEntity department,
        EmployeeEntity manager,
        LocalDate demandDate,
        DemandStatus status,
        String comments
) {
}
