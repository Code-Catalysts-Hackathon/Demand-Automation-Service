package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    List<TeamEntity> findByLabId(Long labId);
}
