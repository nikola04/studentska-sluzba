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
    private Boolean zakljucen;

    @ManyToOne
    private Predmet predmet;
    @ManyToOne
    private Nastavnik nastavnik;
    @ManyToOne
    private IspitniRok ispitniRok;
    @OneToMany(mappedBy = "ispit")
    private List<IspitPrijava> prijaveIspita;
}
