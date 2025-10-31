package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DrziPredmetBezPoklapanjaInitResponse {

    private String fajlPredmetNazivBezPoklapanja;
    private String fajlNastavnikEmailBezPoklapanja;
}