package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.DemandEntity;
import org.automate.demand.ltc.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<DemandEntity, Long> {
}
