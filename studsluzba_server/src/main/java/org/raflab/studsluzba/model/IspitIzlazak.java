package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class IspitIzlazak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double brojPoena;
    private String napomena;
    private Boolean ponisten;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private IspitPrijava ispitPrijava;

    @OneToOne
    private PolozenPredmet polozenPredmet;
}
