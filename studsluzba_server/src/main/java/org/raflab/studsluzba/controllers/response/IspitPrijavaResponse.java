package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IspitPrijavaResponse {
    private Long id;
    private LocalDate datumPrijave;
    private IspitResponse ispit;
    private StudentIndeksResponse studentIndeks;
    private IspitIzlazakResponse ispitIzlazak;
}
