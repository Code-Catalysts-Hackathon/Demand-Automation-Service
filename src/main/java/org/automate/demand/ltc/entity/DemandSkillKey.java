package org.automate.demand.ltc.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DemandSkillKey implements Serializable {
    @Column
    Long demandId;

    @Column
    Long skillId;
}
