package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SrednjaSkolaRequest {
    @NotBlank
    private String naziv;
    @NotBlank
    private String mesto;
    @NotNull
    private Long tipSkoleId;
}
