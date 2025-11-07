package org.raflab.studsluzba.controllers.request;

import lombok.Builder;
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

    private Long predmetId;
    private Long nastavnikId;
    private Long ispitniRokId;
    private Long skolskaGodinaId;
}
