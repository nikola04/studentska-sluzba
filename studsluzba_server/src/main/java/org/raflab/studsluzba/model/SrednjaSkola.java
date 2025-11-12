package org.raflab.studsluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class SrednjaSkola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String naziv;
    @NotBlank
    private String mesto;
    @NotNull
    private TipSkole tipSkole;

    @OneToMany(mappedBy = "srednjaSkola")
    @JsonIgnore
    @ToString.Exclude
    private List<StudentPodaci> studenti;
}
