package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.automate.demand.ltc.domain.DemandStatus;
import org.automate.demand.ltc.domain.Grade;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "demands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandEntity extends BaseEntity{

    @Column(name = "demand_id")
    private String demandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bu_id")
    private BusinessUnitEntity businessUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private PlatformEntity platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lab_id")
    private LabEntity lab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private TeamEntity team;
    private String job_title;
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private EmployeeEntity manager;

    private LocalDate demandDate;

    private DemandStatus status;

    private String comments;

}
