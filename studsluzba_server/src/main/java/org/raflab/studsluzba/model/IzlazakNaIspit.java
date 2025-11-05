package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class IzlazakNaIspit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double brojPoena;
    private boolean ponisten;

    @OneToOne
    private PrijavaIspita prijavaIspita;
}
