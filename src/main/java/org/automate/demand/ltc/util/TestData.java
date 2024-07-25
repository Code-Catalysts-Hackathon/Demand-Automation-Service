package org.automate.demand.ltc.util;

import lombok.Data;
import org.automate.demand.ltc.entity.BusinessUnitEntity;
import org.automate.demand.ltc.entity.EmployeeEntity;
import org.automate.demand.ltc.entity.RoleEntity;
import org.automate.demand.ltc.entity.SkillEntity;

import java.util.List;
@Data
public class TestData {
    List<BusinessUnitEntity> businessUnits;
    List<SkillEntity> skills;
    List<RoleEntity> roles;
    List<EmployeeEntity> employees;
}
