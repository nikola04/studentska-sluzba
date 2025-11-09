package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UplataResponse {
    private Long id;
    private Long studentIndeksId;

    private Double iznos;
    private Double kurs;
    private LocalDate datum;
}
