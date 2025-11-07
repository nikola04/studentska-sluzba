package org.raflab.studsluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class SrednjaSkola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String mesto;
    private TipSkole tipSkole;

    @OneToMany(mappedBy = "srednjaSkola")
    @JsonIgnore
    private List<StudentPodaci> studenti;
}
