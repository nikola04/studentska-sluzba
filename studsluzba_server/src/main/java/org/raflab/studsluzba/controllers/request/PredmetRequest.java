package org.raflab.studsluzba.controllers.request;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Data;
@Data
@NoArgsConstructor
public class PredmetRequest {
        @NonNull
        private String naziv;
        @NonNull
        private Integer espb;
        private Boolean obavezan;
        private String opis;
        @NonNull
        private String sifra;
        private Long studijskiProgramId;
}
