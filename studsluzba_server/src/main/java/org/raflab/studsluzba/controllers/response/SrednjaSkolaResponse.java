package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.TipSkole;

import javax.persistence.ManyToOne;

@Data
public class SrednjaSkolaResponse {
    private Long id;

    private String naziv;
    private String mesto;
    private TipSkole tipSkole;
}
