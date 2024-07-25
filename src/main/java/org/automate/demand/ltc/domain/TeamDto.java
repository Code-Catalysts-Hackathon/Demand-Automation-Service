package org.automate.demand.ltc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private Long labId;
}
