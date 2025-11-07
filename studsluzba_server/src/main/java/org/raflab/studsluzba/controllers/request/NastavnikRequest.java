package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
public class NastavnikRequest {

    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    @NotBlank
    private String srednjeIme;
    @NotNull
    @Email(message = "Field 'email' must be valid Email")
    private String email;
    @NotBlank
    private String brojTelefona;
    @NotBlank
    private String adresa;
    private Set<NastavnikZvanjeRequest> zvanja;

    @NotNull
    private LocalDate datumRodjenja;
    @NotNull
    private Character pol;
    @NotBlank
    @Size(min = 13, max = 13)
    private String jmbg;

    private Set<NastavnikObrazovanjeRequest> obrazovanje;
}
