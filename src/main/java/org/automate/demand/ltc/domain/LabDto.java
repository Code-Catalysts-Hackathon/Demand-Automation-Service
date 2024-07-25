package org.automate.demand.ltc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LabDto {
    private Long id;
    private String name;
    private Long platformId;
}
