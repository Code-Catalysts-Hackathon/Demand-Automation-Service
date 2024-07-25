package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long createdBy;

    @CreationTimestamp
    private Date createdDate;

    private Long updatedBy;

    @CreationTimestamp
    private Date updatedDate;

    private boolean deleted;
}
