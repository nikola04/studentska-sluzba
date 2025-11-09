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

    private Integer godina;
    private Boolean aktivan;

    @OneToMany(mappedBy = "skolskaGodina")
    private List<IspitniRok> ispitiRokovi;
}
