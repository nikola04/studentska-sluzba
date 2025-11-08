package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.PredispitneObavezeVrsta;

@Data
public class PredispitnaObavezaResponse {
    private Long id;
    private Long predmetId;
    private SkolskaGodinaResponse skolskaGodina;
    private PredispitneObavezeVrsta vrsta;
    private Double maxBrojPoena;
}