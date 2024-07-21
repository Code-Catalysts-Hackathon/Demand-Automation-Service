package org.automate.demand.ltc.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class StudentEntity extends BaseEntity{

    private String firstName;
    private String lastName;

}
