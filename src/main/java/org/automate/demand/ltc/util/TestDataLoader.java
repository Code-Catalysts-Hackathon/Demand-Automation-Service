package org.automate.demand.ltc.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.automate.demand.ltc.repository.BusinessUnitRepository;
import org.automate.demand.ltc.repository.EmployeeRepository;
import org.automate.demand.ltc.repository.RoleRepository;
import org.automate.demand.ltc.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Service
@AllArgsConstructor
@Transactional
public class TestDataLoader implements CommandLineRunner {
    private final BusinessUnitRepository businessUnitRepository;
    private final SkillRepository skillRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading Test Data");
        File file = ResourceUtils.getFile("classpath:test_data.json");
        TestData testData = objectMapper.readValue(file, TestData.class);
        testData.getBusinessUnits().forEach(businessUnitEntity -> {
            if (!CollectionUtils.isEmpty(businessUnitEntity.getPlatforms())) {
                businessUnitEntity.getPlatforms().forEach(platformEntity -> {
                    platformEntity.setBusinessUnit(businessUnitEntity);
                    if (!CollectionUtils.isEmpty(platformEntity.getLabs())) {
                        platformEntity.getLabs().forEach(labEntity -> {
                            labEntity.setPlatform(platformEntity);
                            if (!CollectionUtils.isEmpty(labEntity.getTeams())) {
                                labEntity.getTeams().forEach(teamEntity -> {
                                    teamEntity.setLab(labEntity);
                                });
                            }
                        });
                    }
                });
            }
        });
        businessUnitRepository.saveAll(testData.businessUnits);
        skillRepository.saveAll(testData.getSkills());
        roleRepository.saveAll(testData.getRoles());
        employeeRepository.saveAll(testData.getEmployees());
    }
}
