package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PolozenPredmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ocena;

    @ManyToOne
    private Predmet predmet;

    @ManyToOne
    private StudentIndeks studentIndeks;

    @OneToOne
    private IspitIzlazak ispitIzlazak; // if null - from other college
}
