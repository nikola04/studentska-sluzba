package org.raflab.studsluzba.controllers.request;

import lombok.Data;

@Data
public class PolozenPredmetRequest {
    private Long ispitIzlazakId;
    private Integer ocena; // only if ispitIzlazakId is null
}
