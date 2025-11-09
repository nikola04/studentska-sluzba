package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Uplata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double iznosDinari;
    private Double kurs;
    private LocalDate datumUplate;

    @ManyToOne
    private StudentIndeks studentIndeks;
}
