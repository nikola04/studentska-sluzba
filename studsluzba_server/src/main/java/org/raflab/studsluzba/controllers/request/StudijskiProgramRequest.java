package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.validators.ValidStudijskiProgram;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ValidStudijskiProgram
public class StudijskiProgramRequest {
    @NotBlank
    private String oznaka;
    @NotBlank
    private String naziv;
    @NotNull
    private Integer godinaAkreditacije;
    @NotBlank
    private String zvanje;
    @NotNull
    @Positive
    private Integer trajanjeGodina;
    @NotNull
    @Positive
    private Integer trajanjeSemestara;
    @NotNull
    private Long vrstaStudija;
    @NotNull
    @Positive
    private Integer ukupnoEspb;
}
