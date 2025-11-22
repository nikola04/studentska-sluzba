package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class NastavnikZvanjeRequest {
    @NotNull
    private LocalDate datumIzbora;
    @NotNull
    private Long naucnaOblastId;
    @NotNull
    private Long uzaNaucnaOblastId;
    @NotNull
    private Long zvanjeId;
    @NotNull
    private Boolean aktivno;
}