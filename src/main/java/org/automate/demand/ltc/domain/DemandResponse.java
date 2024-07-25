package org.automate.demand.ltc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DemandResponse {
    List<DemandDto> list;
    int totalEntries;
}
