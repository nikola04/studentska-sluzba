package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class IspitPrijava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumPrijave;

    @ManyToOne
    private Ispit ispit;

    @OneToOne
    private IspitIzlazak ispitIzlazak;

    @OneToOne
    private PolozenPredmet polozenPredmet;
}
