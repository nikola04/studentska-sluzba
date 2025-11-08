package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.StudentIndeksRequest;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentIndeksMapper {
    @Autowired
    StudijskiProgramMapper studijskiProgramMapper;

    public StudentIndeks toEntity(StudentIndeksRequest request) {
        StudentIndeks studentIndeks = new StudentIndeks();

        if(request.getGodina() != null)
            studentIndeks.setGodina(request.getGodina());
        else {
            int year = LocalDate.now().getYear();
            studentIndeks.setGodina(year);
        }
        studentIndeks.setNacinFinansiranja(request.getNacinFinansiranja());
        studentIndeks.setAktivan(request.getAktivan());
        if(request.getVaziOd() != null)
            studentIndeks.setVaziOd(request.getVaziOd());
        else
            studentIndeks.setVaziOd(LocalDate.now());
        studentIndeks.setOstvarenoEspb(0);

        return studentIndeks;
    }

    public StudentIndeksResponse toResponse(StudentIndeks studentIndeks) {
        StudentIndeksResponse studentIndeksResponse = new StudentIndeksResponse();

        studentIndeksResponse.setId(studentIndeks.getId());
        studentIndeksResponse.setBroj(studentIndeks.getBroj());
        studentIndeksResponse.setGodina(studentIndeks.getGodina());
        studentIndeksResponse.setNacinFinansiranja(studentIndeks.getNacinFinansiranja());
        studentIndeksResponse.setAktivan(studentIndeks.getAktivan());
        studentIndeksResponse.setVaziOd(studentIndeks.getVaziOd());
        studentIndeksResponse.setOstvarenoEspb(studentIndeks.getOstvarenoEspb());
        studentIndeksResponse.setStudijskiProgram(studijskiProgramMapper.toResponse(studentIndeks.getStudijskiProgram()));

        return studentIndeksResponse;
    }

    public List<StudentIndeksResponse> toResponseList(List<StudentIndeks> studentIndeksIterable) {
        return studentIndeksIterable.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
