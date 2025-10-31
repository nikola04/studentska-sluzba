package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import lombok.Getter;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.model.StudijskiProgram;

import java.time.LocalDate;

@Data
public class StudentIndeksResponse {
    private Long id;
    private int broj;
    private int godina;
    private String studProgramOznaka;
    private String nacinFinansiranja;
    private boolean aktivan;
    private LocalDate vaziOd;
    private StudentPodaci student;
    private StudijskiProgram studijskiProgram;   // na koji studijski program je upisan
    private Integer ostvarenoEspb;
}
