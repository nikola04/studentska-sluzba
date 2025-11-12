package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SkolskaGodina that = (SkolskaGodina) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
