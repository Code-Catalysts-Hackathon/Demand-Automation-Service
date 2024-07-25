package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity extends BaseEntity{

    private String name;
    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<DemandEntity> demands;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_id",referencedColumnName = "id",nullable = false)
    private LabEntity lab;

}
