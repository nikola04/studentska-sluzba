package org.raflab.studsluzba.controllers.response;

import lombok.Data;
@Data
public class PredmetResponse {
    private Long id;
    private String naziv;
    private Integer espb;
    private Boolean obavezan;
    private String opis;
    private String sifra;
    private StudijskiProgramResponse studijskiProgram;
    private Integer fondCasovaVezbe;
    private Integer fondCasovaPredavanja;
}
