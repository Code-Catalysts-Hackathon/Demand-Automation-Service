package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "demand_skill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandSkillEntity {

    @EmbeddedId
    DemandSkillKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("demandId")
    @JoinColumn(name = "demand_id")
    private DemandEntity demand;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private SkillEntity skill;

}
