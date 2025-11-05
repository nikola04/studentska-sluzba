package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class StudentPredispitnaObaveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double brojPoena;

    @ManyToOne
    private StudentIndeks studentIndeks;

    @ManyToOne
    private PredispitnaObaveza predispitnaObaveza;

    @ManyToOne
    private SkolskaGodina skolskaGodina;
}
