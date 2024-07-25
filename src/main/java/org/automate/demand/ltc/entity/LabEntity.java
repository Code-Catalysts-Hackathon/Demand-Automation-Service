package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "labs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabEntity extends  BaseEntity {
    private String name;
    @OneToMany(mappedBy = "lab",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<TeamEntity> teams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id",referencedColumnName = "id",nullable = false)
    private PlatformEntity platform;
}

