package org.raflab.studsluzba.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class StudijskiProgram {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String oznaka;  // RN, RI, SI, MD
	private String naziv;   
	private Integer godinaAkreditacije;
	private String zvanje;
	private Integer trajanjeGodina;
	private Integer trajanjeSemestara;
    private Integer ukupnoEspb;

    @ManyToOne
	private VrstaStudija vrstaStudija;

    @OneToMany(mappedBy = "studijskiProgram")
    private List<StudentIndeks> studentIndeksi;
	
    @OneToMany(mappedBy = "studProgram", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Predmet> predmeti;
}
