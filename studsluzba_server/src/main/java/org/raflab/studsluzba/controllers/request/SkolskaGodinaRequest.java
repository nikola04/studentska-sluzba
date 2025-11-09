package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class SkolskaGodinaRequest {
    @NotNull
    @PositiveOrZero
    private Integer godina;
    private Boolean aktivan;
}
