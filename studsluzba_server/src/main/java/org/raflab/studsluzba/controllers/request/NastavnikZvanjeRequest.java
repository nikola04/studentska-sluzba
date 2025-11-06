package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NastavnikZvanjeRequest {
    private LocalDate datumIzbora;
    private String naucnaOblast;
    private String uzaNaucnaOblast;
    private String zvanje;
    private Boolean aktivno;
}