package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class IspitResponse {
    private LocalDate datumOdrzavanja;
    private LocalTime vremePocetka;
    private Boolean zakljucen;
}
