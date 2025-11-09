package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class SkolskaGodinaResponse {
    private Long id;
    private Integer godina;
    private Boolean aktivan;
}
