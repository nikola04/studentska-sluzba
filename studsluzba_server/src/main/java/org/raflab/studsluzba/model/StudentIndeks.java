package org.raflab.studsluzba.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"broj", "godina", "studijski_program_id", "aktivan"}))
public class StudentIndeks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer broj;
	private Integer godina;
	private NacinFinansiranja nacinFinansiranja;
	private Boolean aktivan;
	private LocalDate vaziOd;
    private Integer ostvarenoEspb;

    @ManyToOne
	private StudentPodaci student;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;

    @OneToMany(mappedBy="studentIndeks")
    private List<PredmetSlusa> predmetiSlusa;

    @OneToMany(mappedBy="studentIndeks")
    private List<PolozenPredmet> polozeniPredmeti;
}
