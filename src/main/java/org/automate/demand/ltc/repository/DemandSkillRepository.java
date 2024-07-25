package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.DemandSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DemandSkillRepository extends JpaRepository<DemandSkillEntity, Long> {

    Optional<Set<DemandSkillEntity>> findByDemandId(Long id);
}
