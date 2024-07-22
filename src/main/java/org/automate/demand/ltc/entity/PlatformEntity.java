package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "platforms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatformEntity extends  BaseEntity{

    private String name;
    @OneToMany(mappedBy = "platform",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<LabEntity> labs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bu_id",referencedColumnName = "id",nullable = false)
    private BusinessUnitEntity businessUnit;
}
