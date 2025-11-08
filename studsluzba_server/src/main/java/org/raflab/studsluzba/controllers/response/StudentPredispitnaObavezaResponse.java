package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class StudentPredispitnaObavezaResponse {
    private Long id;
    private Double brojPoena;
    private PredispitnaObavezaResponse predispitnaObaveza;
}
