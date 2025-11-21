package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SrednjaSkolaMapper {
    public SrednjaSkola toEntity(SrednjaSkolaRequest request){
        SrednjaSkola srednjaSkola = new SrednjaSkola();

        srednjaSkola.setNaziv(request.getNaziv());
        srednjaSkola.setMesto(request.getMesto());

        return srednjaSkola;
    }

    public SrednjaSkolaResponse toResponse(SrednjaSkola srednjaSkola){
        SrednjaSkolaResponse response = new SrednjaSkolaResponse();
        response.setId(srednjaSkola.getId());
        response.setNaziv(srednjaSkola.getNaziv());
        response.setMesto(srednjaSkola.getMesto());
        response.setTipSkole(srednjaSkola.getTipSkole());

        return response;
    }

    public List<SrednjaSkolaResponse> toResponseList(List<SrednjaSkola> srednjaSkolaList){
        return srednjaSkolaList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
