package org.automate.demand.ltc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseEntity {
    @Column(name = "token_value")
    private String value;
    @ManyToOne
    private User user;
    private Date expiryAt;

}
