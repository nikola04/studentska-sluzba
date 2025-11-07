package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IspitniRokResponse {
    private LocalDate pocetak;
    private LocalDate kraj;
    private SkolskaGodinaResponse skolskaGodina;
}
