package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpisGodineResponse {
    private Long id;

    private Integer godina;
    private LocalDate datumUpisa;
    private String napomena;

    private SkolskaGodinaResponse skolskaGodina;
}
