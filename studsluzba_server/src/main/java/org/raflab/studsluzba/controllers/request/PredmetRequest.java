package org.raflab.studsluzba.controllers.request;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Data;
@Data
@NoArgsConstructor
public class PredmetRequest {
        @NonNull
        private String naziv;
        private int espb;
        private boolean obavezan;
        private String opis;
        private String sifra;
        private Long studijskiProgramId;
}
