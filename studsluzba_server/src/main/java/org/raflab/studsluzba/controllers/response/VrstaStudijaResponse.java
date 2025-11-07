package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class VrstaStudijaResponse {
    private Long id;

    private String naziv;
    private String oznaka;
}
