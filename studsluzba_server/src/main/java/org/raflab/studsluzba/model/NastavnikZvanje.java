package org.raflab.studsluzba.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class NastavnikZvanje {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate datumIzbora;
    @ManyToOne
	private NaucnaOblast naucnaOblast;
    @ManyToOne
	private UzaNaucnaOblast uzaNaucnaOblast;
    @ManyToOne
	private Zvanje zvanje;
	private boolean aktivno;
	@ManyToOne
    @EqualsAndHashCode.Exclude
	private Nastavnik nastavnik;
}
