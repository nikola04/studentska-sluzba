package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PredispitnaObaveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PredispitneObavezeVrsta vrsta;
    private double maxBrojPoena;

    @ManyToOne
    private Predmet predmet;
    @ManyToOne
    private SkolskaGodina skolskaGodina;
}
