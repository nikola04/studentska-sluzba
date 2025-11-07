package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class VisokoskolskaUstanova {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @OneToMany(mappedBy = "visokoskolskaUstanova")
    private List<StudentPodaci> studenti;
}
