package org.automate.demand.ltc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BusinessUnitDto {
    private Long id;
    private String name;
    private String shortCode;
    private String description;
}
