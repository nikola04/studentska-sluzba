package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class ObnovaGodine {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumObnove;
    @Column(length = 500)
    private String napomena;
    private Integer godina;

    @ManyToOne(optional = false)
    private StudentIndeks studentIndeks;
    @ManyToOne(optional = false)
    private SkolskaGodina skolskaGodina;

    @ManyToMany
    @JoinTable(
            name = "obnova_godine_predmeti",
            joinColumns = @JoinColumn(name = "obnova_godine_id"),
            inverseJoinColumns = @JoinColumn(name = "predmet_id")
    )
    private List<Predmet> obnovljeniPredmeti;
}