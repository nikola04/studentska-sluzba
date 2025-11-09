package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class IspitRezultatResponse {
    private StudentIndeksResponse studentIndeks;
    private Double brojPoenaUkupno;
    private Double brojPoenaIspit;
    private Double brojPoenaPredispitne;
}
