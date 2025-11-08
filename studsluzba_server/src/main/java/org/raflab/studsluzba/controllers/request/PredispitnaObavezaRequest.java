package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.PredispitneObavezeVrsta;

import javax.validation.constraints.NotNull;

@Data
public class PredispitnaObavezaRequest {
    @NotNull
    private Long skolskaGodinaId;
    @NotNull
    private PredispitneObavezeVrsta vrsta;
    @NotNull
    private Double maxBrojPoena;
}
