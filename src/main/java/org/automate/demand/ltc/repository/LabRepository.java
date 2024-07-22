package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.LabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends JpaRepository<LabEntity, Long> {
}
