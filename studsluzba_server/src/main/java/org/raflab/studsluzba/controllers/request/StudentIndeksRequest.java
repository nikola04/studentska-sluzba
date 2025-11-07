package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class StudentIndeksRequest {
    @NotNull
    private int godina; //ako npr student mastera upisuje neki program, a godina presla u sledecu???
    @NotNull
    private String studProgramOznaka;
    @NotNull
    private String nacinFinansiranja;
    private boolean aktivan;
    private LocalDate vaziOd;   //TODO - da li da setujm trenutni datum
    @NotNull
    private Long studentId;
}