package org.automate.demand.ltc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_id")
    private DemandEntity demand;

    private String name;
    private String email;
    private String mobile;

    private long createdBy;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private long updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
