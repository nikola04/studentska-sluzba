package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class UpisGodine {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumUpisa;
    @Column(length = 500)
    private String napomena;

    @ManyToOne(optional = false)
    private StudentIndeks studentIndeks;
    @ManyToOne(optional = false)
    private SkolskaGodina skolskaGodina;

    @ManyToMany
    @JoinTable(
            name = "upis_godine_predmeti",
            joinColumns = @JoinColumn(name = "upis_godine_id"),
            inverseJoinColumns = @JoinColumn(name = "predmet_id")
    )
    private List<Predmet> predmeti;
}