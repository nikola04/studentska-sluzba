package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class IspitIzlazakResponse {
    private Long id;
    private Double brojPoena;
    private String napomena;
    private Boolean ponisten;
    private IspitPrijavaResponse ispitPrijava;
}
