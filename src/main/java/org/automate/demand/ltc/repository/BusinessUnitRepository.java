package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.BusinessUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnitEntity, Long> {


}
