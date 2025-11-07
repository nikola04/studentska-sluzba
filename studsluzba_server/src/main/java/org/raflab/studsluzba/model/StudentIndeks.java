package org.raflab.studsluzba.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"broj", "godina", "studProgramOznaka", "aktivan"}))
public class StudentIndeks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer broj;
	private Integer godina;
	private String studProgramOznaka;
	private String nacinFinansiranja;
	private Boolean aktivan;
	private LocalDate vaziOd;

	@ManyToOne
	private StudentPodaci student;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;
	private Integer ostvarenoEspb;

    @OneToMany(mappedBy="studentIndeks")
    private List<SlusaPredmet> slusaPredmeti;

    @OneToMany(mappedBy="studentIndeks")
    private List<PolozenPredmet> polozeniPredmeti;
}
