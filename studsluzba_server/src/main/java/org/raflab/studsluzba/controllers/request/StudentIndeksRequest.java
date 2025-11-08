package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.NacinFinansiranja;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class StudentIndeksRequest {
    private Integer godina; // will use current year if not provided
    @NotNull
    private Long studProgramId;
    @NotNull
    private NacinFinansiranja nacinFinansiranja;
    @NotNull
    private Boolean aktivan;
    private LocalDate vaziOd; // set date if not provided
}