package org.automate.demand.ltc.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class DemandSkillKey implements Serializable {
    @Column
    Long demandId;

    @Column
    Long skillId;
}
