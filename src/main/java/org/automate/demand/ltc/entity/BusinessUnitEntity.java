package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "business_units")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUnitEntity extends BaseEntity{
    private String name;
    private String shortCode;
    private String description;

    @OneToMany(mappedBy = "businessUnit" , fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<PlatformEntity> platforms;
}
