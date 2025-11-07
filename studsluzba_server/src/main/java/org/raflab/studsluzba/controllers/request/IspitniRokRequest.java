package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;
import org.raflab.studsluzba.validators.ValidDateRange;

import java.time.LocalDate;

@Data
@ValidDateRange
public class IspitniRokRequest {
    @NonNull
    private LocalDate pocetak;
    @NonNull
    private LocalDate kraj;

    @NonNull
    private Long skolskaGodinaId;
}
