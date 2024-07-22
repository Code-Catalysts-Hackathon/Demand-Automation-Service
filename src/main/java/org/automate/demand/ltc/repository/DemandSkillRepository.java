package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.DemandSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandSkillRepository extends JpaRepository<DemandSkillEntity, Long> {
}
