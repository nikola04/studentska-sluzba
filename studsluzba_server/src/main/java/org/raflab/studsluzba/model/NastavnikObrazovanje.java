package org.raflab.studsluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class NastavnikObrazovanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Nastavnik nastavnik;

    @ManyToOne
    private VisokoskolskaUstanova visokoskolskaUstanova;

    @ManyToOne
    private VrstaStudija vrstaStudija;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NastavnikObrazovanje that = (NastavnikObrazovanje) o;
        return Objects.equals(nastavnik, that.nastavnik) && Objects.equals(visokoskolskaUstanova, that.visokoskolskaUstanova) && Objects.equals(vrstaStudija, that.vrstaStudija);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nastavnik, visokoskolskaUstanova, vrstaStudija);
    }
}
