package org.raflab.studsluzba.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Data
public class StudentPodaci {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 private String ime;	  // not null
	 private String prezime;  // not null
	 private String srednjeIme;   // not null 
	 private String jmbg;    
	 private LocalDate datumRodjenja;  // not null
	 private String mestoRodjenja; 
	 private String mestoPrebivalista;  // not null
	 private String drzavaRodjenja;   
	 private String drzavljanstvo;   // not null
	 private String nacionalnost;   // samoizjasnjavanje, moze bilo sta  
	 private Character pol;    // not null
	 private String adresa;  // not null
	 private String brojTelefonaMobilni;  
	 private String brojTelefonaFiksni;
     private String privatniEmail;
     private String fakultetEmail;
	 private String brojLicneKarte;
	 private String licnuKartuIzdao;
	 private String mestoStanovanja;
	 private String adresaStanovanja;   // u toku studijaq

    @ManyToOne
    private SrednjaSkola srednjaSkola;
    private Double uspehSrednjaSkola, uspehPrijemni;

    @ManyToOne
    private VisokoskolskaUstanova visokoskolskaUstanova;
}
