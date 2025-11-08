package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class DrziPredmetResponse {
    private Long id;
    private Long skolskaGodinaId;
    private Long predmetId;
    private Long nastavnikId;
    private PredmetResponse predmet;
    private NastavnikResponse nastavnik;
}
