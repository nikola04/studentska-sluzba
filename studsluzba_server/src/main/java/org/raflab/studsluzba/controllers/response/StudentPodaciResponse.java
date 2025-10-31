package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentPodaciResponse {
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
    private String email;  // not null
    private String brojLicneKarte;
    private String licnuKartuIzdao;
    private String mestoStanovanja;
    private String adresaStanovanja;   // u toku studija

}
