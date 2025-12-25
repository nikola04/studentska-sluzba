package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class StudentPodaciRequest {

    @NotBlank
    private String ime;	  // not null
    @NotBlank
    private String prezime;  // not null
    @NotBlank
    private String srednjeIme;   // not null
    @NotBlank
    @Length(min = 13, max = 13)
    private String jmbg;
    @NotNull
    private Integer godinaUpisa;
    @NotNull
    private LocalDate datumRodjenja;  // not null
    @NotBlank
    private String mestoRodjenja;
    @NotBlank
    private String drzavaRodjenja;
    @NotBlank
    private String drzavljanstvo;   // not null
    private String nacionalnost;   // can be null if user doesnt want to specify it?
    @NotNull
    private Character pol;    // not null
    @NotBlank
    @Pattern(regexp = "^\\+\\d+$", message = "Field 'brojTelefonaMobilni' must start with '+' and contain only digits")
    private String brojTelefonaMobilni;
    @NotBlank
    @Email(message = "Field 'fakultetEmail' must be valid Email")
    private String fakultetEmail;
    @NotBlank
    @Email(message = "Field 'privatniEmail' must be valid Email")
    private String privatniEmail;
    @NotBlank
    private String brojLicneKarte;
    @NotBlank
    private String mestoStanovanja;
    @NotBlank
    private String adresaStanovanja;

    @NotNull
    private Double uspehSrednjaSkola;
    @NotNull
    private Double uspehPrijemni;
    @NotNull
    private Long srednjaSkolaId;

    private Long visokoskolskaUstanovaId;
}
