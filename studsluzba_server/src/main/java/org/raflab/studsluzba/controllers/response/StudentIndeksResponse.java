package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.NacinFinansiranja;

import java.time.LocalDate;

@Data
public class StudentIndeksResponse {
    private Long id;
    private Integer broj;
    private Integer godina;
    private NacinFinansiranja nacinFinansiranja;
    private Boolean aktivan;
    private LocalDate vaziOd;
    private StudijskiProgramResponse studijskiProgram;
    private Integer ostvarenoEspb;
    private StudentPodaciResponse student;
}
