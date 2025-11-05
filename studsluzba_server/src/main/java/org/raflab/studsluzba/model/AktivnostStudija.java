package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class AktivnostStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TokStudija tokStudija;

    @Enumerated(EnumType.STRING)
    private TipAktivnosti tip;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    private LocalDate datum;

    private String napomena;

    @ManyToMany
    @JoinTable(
            name = "aktivnost_predmeti",
            joinColumns = @JoinColumn(name = "aktivnost_id"),
            inverseJoinColumns = @JoinColumn(name = "predmet_id")
    )

    private List<Predmet> predmeti;
}
