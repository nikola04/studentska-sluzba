package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import lombok.Getter;
import org.raflab.studsluzba.model.NacinFinansiranja;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.model.StudijskiProgram;

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
}
