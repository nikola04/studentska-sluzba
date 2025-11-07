package org.raflab.studsluzba.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Data
public class Nastavnik {
	 
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 private String ime;	  // not null
	 private String prezime;  // not null
	 private String srednjeIme;   // not null 
	 private String email;   // not null
	 private String brojTelefona;
	 private String adresa;

     @OneToMany(mappedBy = "nastavnik", cascade = CascadeType.ALL, orphanRemoval = true)
     @EqualsAndHashCode.Exclude
     private Set<NastavnikObrazovanje> obrazovanja;

	 @OneToMany(mappedBy = "nastavnik", cascade = CascadeType.ALL, orphanRemoval = true)
     @EqualsAndHashCode.Exclude
     private Set<NastavnikZvanje> zvanja;

     @OneToMany(mappedBy = "nastavnik")
     @EqualsAndHashCode.Exclude
     private Set<DrziPredmet> drziPredmeteList;
	 
	 private LocalDate datumRodjenja;
	 private Character pol;
	 private String jmbg;

}
