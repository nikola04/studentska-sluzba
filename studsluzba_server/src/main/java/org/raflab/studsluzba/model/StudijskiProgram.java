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
	private String oznaka;  // RN, RM
	private String naziv;   
	private Integer godinaAkreditacije;
	private String zvanje;
	private Integer trajanjeGodina;
	private Integer trajanjeSemestara;
	private String vrstaStudija; // OAS - osnovne akademske studje, OSS - osnovne strukovne, 	MAS - master akademske studije
	private Integer ukupnoEspb;
	
	@JsonIgnore
	@OneToMany(mappedBy = "studProgram")
	private List<Predmet> predmeti;
}
