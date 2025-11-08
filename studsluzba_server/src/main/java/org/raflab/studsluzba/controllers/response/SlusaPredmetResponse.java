package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class SlusaPredmetResponse {
    private Long id;
    private Long studentIndeksId;
    private DrziPredmetResponse drziPredmet;
}
