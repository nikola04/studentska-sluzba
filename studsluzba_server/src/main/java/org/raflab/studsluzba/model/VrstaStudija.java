package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class VrstaStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String oznaka;

    @OneToMany(mappedBy = "vrstaStudija")
    private List<StudijskiProgram> studijskiProgrami;
}
