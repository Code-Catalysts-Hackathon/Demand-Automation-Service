package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.DemandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<DemandEntity, Long> {
    DemandEntity findByDemandId(String demandId);
}
