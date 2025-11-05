package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TokStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentIndeks studentIndeks;

    @ManyToOne
    private StudijskiProgram program;

    @OneToMany(mappedBy = "tokStudija", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AktivnostStudija> aktivnosti;
}