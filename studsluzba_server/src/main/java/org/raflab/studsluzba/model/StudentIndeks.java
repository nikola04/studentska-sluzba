package org.raflab.studsluzba.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"broj", "godina", "studijski_program_id"}))
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
    @ToString.Exclude
    private StudentPodaci student;
	
	@ManyToOne
    @ToString.Exclude
    private StudijskiProgram studijskiProgram;

    @OneToMany(mappedBy="studentIndeks")
    private List<PredmetSlusa> predmetiSlusa;

    @OneToMany(mappedBy="studentIndeks")
    private List<IspitPrijava> prijaveIspita;

    @OneToMany(mappedBy="studentIndeks")
    private List<PolozenPredmet> polozeniPredmeti;

    @OneToMany(mappedBy="studentIndeks")
    private List<Uplata> uplate;
}
