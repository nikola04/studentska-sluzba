package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.PositiveOrZero;

@Data
public class IspitIzlazakRequest {
    @NonNull
    @PositiveOrZero
    private Double brojPoena;
    @NonNull
    private Boolean ponisten;
}
