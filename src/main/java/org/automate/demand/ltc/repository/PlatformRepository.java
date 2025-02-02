package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Long> {
}
