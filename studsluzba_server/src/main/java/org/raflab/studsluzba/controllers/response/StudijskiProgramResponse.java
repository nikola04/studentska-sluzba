package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class StudijskiProgramResponse {
    private Long id;
    private String oznaka;
    private String naziv;
    private Integer godinaAkreditacije;
    private String zvanje;
    private Integer trajanjeGodina;
    private Integer trajanjeSemestara;
    private VrstaStudijaResponse vrstaStudija;
    private Integer ukupnoEspb;
    private Long predmetiSize;
}
