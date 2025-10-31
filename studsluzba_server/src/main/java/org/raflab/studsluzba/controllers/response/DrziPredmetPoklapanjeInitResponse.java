package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DrziPredmetPoklapanjeInitResponse {
    private Long predmetId;
    private String predmetNaziv;

    private String fajlPredmetNaziv;
    private String fajlNastavnikEmail;
}