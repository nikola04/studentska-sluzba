package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SkolskaGodina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean aktivan;

    @OneToMany(mappedBy = "skolskaGodina")
    private List<DrziPredmet> nastavnikPredmeti;
    @OneToMany(mappedBy = "skolskaGodina")
    private List<SlusaPredmet> slusaPredmeti;
    @OneToMany(mappedBy = "skolskaGodina")
    private List<Ispit> ispiti;
    @OneToMany(mappedBy = "skolskaGodina")
    private List<AktivnostStudija> aktivnostiStudija;
}
