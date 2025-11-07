package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.model.StudentPodaci;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {
//    public StudentPodaci toEntity(Student request) {
//        SkolskaGodina skolskaGodina = new SkolskaGodina();
//
//        return skolskaGodina;
//    }
//
//    public SkolskaGodinaResponse toResponse(SkolskaGodina skolskaGodina) {
//        SkolskaGodinaResponse skolskaGodinaResponse = new SkolskaGodinaResponse();
//        skolskaGodinaResponse.setId(skolskaGodina.getId());
//        skolskaGodinaResponse.setAktivan(skolskaGodina.getAktivan());
//        return skolskaGodinaResponse;
//    }
//
//    public List<SkolskaGodinaResponse> toResponseList(Iterable<SkolskaGodina> skolskaGodinaIterable) {
//        List<SkolskaGodinaResponse> skolskaGodinaResponses = new ArrayList<>();
//        skolskaGodinaIterable.forEach((skolskaGodina) -> {
//            skolskaGodinaResponses.add(toResponse(skolskaGodina));
//        });
//        return skolskaGodinaResponses;
//    }
}
