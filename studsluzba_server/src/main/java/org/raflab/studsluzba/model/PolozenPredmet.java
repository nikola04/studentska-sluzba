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

    @OneToOne
    private PrijavaIspita prijavaIspita;

    @ManyToOne
    private StudentIndeks studentIndeks;

    @ManyToOne
    private Predmet predmet;
}
