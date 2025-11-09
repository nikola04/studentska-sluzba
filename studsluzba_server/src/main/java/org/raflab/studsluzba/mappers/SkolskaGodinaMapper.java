package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkolskaGodinaMapper {
    public SkolskaGodina toEntity(SkolskaGodinaRequest request) {
        SkolskaGodina skolskaGodina = new SkolskaGodina();

        skolskaGodina.setAktivan(request.getAktivan());
        skolskaGodina.setGodina(request.getGodina());

        return skolskaGodina;
    }

    public SkolskaGodinaResponse toResponse(SkolskaGodina skolskaGodina) {
        SkolskaGodinaResponse skolskaGodinaResponse = new SkolskaGodinaResponse();

        skolskaGodinaResponse.setId(skolskaGodina.getId());
        skolskaGodinaResponse.setGodina(skolskaGodina.getGodina());
        skolskaGodinaResponse.setAktivan(skolskaGodina.getAktivan());

        return skolskaGodinaResponse;
    }

    public List<SkolskaGodinaResponse> toResponseList(List<SkolskaGodina> skolskaGodinaList) {
        return skolskaGodinaList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}