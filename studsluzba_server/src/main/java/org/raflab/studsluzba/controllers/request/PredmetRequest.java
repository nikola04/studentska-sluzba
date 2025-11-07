package org.raflab.studsluzba.controllers.request;

import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PredmetRequest {
        @NotBlank
        private String naziv;
        @NotNull
        private Integer espb;
        @NotNull
        private Boolean obavezan;
        private String opis; // can be null
        @NotBlank
        private String sifra;
        @NotNull
        private Long studijskiProgramId;
}
