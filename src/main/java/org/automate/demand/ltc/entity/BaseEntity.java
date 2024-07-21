package org.automate.demand.ltc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @Generated
    private Long id;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
