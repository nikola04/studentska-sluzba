package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class IspitRequest {
    @NonNull
    private LocalDate datumOdrzavanja;
    @NonNull
    private LocalTime vremePocetka;
    private Boolean zakljucen;

    @NonNull
    private Long predmetId;
    @NonNull
    private Long nastavnikId;
    @NonNull
    private Long ispitniRokId;
    @NonNull
    private Long skolskaGodinaId;
}
