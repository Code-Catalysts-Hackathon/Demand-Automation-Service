package org.automate.demand.ltc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlatformDto {
    private Long id;
    private String name;
    private long buId;
}

