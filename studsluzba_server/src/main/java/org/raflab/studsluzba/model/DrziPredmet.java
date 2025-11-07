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

    @OneToMany(mappedBy = "drziPredmet")
    private List<SlusaPredmet> slusajuPredmtList;
}
