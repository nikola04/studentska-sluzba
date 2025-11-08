package org.raflab.studsluzba.model;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class DrziPredmet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Nastavnik nastavnik;
	
	@ManyToOne
	private Predmet predmet;

    @ManyToOne
    private SkolskaGodina skolskaGodina;

    @OneToMany(mappedBy = "drziPredmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PredmetSlusa> slusajuPredmtList;
}
