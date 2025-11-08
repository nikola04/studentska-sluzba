package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class PolozenPredmetResponse {
    private Long id;
    private Integer ocena;
    private PredmetResponse predmet;
    private IspitIzlazakResponse ispitIzlazak;
}
