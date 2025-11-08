package org.raflab.studsluzba.model;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
public class Grupa {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "grupa_predmeti",
            joinColumns = @JoinColumn(name = "grupa_id"),
            inverseJoinColumns = @JoinColumn(name = "predmeti_id")
    )
    private List<Predmet> predmeti;
}
