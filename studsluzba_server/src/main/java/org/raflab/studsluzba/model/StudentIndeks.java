package org.raflab.studsluzba.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"broj", "godina", "studProgramOznaka", "aktivan"}))
public class StudentIndeks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int broj;
	private int godina;
	private String studProgramOznaka;
	private String nacinFinansiranja;
	private boolean aktivan; 
	private LocalDate vaziOd;
	@ManyToOne
	private StudentPodaci student;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;   // na koji studijski program je upisan
	private Integer ostvarenoEspb;

}
