package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.PredispitnaObavezaVrsta;

@Data
public class PredispitnaObavezaResponse {
    private Long id;
    private Long predmetId;
    private SkolskaGodinaResponse skolskaGodina;
    private PredispitnaObavezaVrsta vrsta;
    private Double maxBrojPoena;
}