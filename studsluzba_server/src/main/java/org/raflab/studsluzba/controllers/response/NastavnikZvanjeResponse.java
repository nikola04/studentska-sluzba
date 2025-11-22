package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.NaucnaOblast;
import org.raflab.studsluzba.model.UzaNaucnaOblast;
import org.raflab.studsluzba.model.Zvanje;

import java.time.LocalDate;

@Data
public class NastavnikZvanjeResponse {
    private Long id;
    private LocalDate datumIzbora;
    private NaucnaOblast naucnaOblast;
    private UzaNaucnaOblast uzaNaucnaOblast;
    private Zvanje zvanje;
    private Boolean aktivno;
}