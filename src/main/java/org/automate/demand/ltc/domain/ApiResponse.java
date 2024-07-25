package org.automate.demand.ltc.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private Object data;
}
