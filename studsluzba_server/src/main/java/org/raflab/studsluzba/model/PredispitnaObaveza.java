package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PredispitnaObaveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PredispitnaObavezaVrsta vrsta;
    private Double maxBrojPoena;

    @ManyToOne
    private Predmet predmet;
    @ManyToOne
    private SkolskaGodina skolskaGodina;
}
