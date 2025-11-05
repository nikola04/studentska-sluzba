package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Ispit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumOdrzavanja;
    private LocalTime vremePocetka;
    private boolean zakljucen;

    @ManyToOne
    private Predmet predmet;
    @ManyToOne
    private Nastavnik nastavnik;
    @ManyToOne
    private IspitniRok ispitniRok;
    @ManyToOne
    private SkolskaGodina skolskaGodina;
    @OneToMany
    private List<PrijavaIspita> prijaveIspita;
}
