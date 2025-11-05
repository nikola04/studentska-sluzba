package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class PrijavaIspita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumPrijave;

    @ManyToOne
    private Ispit ispit;

    @OneToOne
    private IzlazakNaIspit izlazakNaIspit;

    @OneToOne
    private PolozenPredmet polozenPredmet;
}
