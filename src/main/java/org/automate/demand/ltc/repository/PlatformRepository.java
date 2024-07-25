package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Long> {
    List<PlatformEntity> findByBusinessUnitId(Long businessUnitId);
}
