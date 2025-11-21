package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PredispitnaObavezaRequest {
    @NotNull
    private Long skolskaGodinaId;
    @NotNull
    private Long predispitnaObavezaVrstaId;
    @NotNull
    private Double maxBrojPoena;
}
