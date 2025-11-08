package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.model.Ispit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IspitMapper {
    public Ispit toEntity(IspitRequest request){
        Ispit ispit = new Ispit();

        ispit.setDatumOdrzavanja(request.getDatumOdrzavanja());
        ispit.setVremePocetka(request.getVremePocetka());
        ispit.setZakljucen(request.getZakljucen() != null ? request.getZakljucen() : false);

        return ispit;
    }

    public IspitResponse toResponse(Ispit entity){
        IspitResponse response = new IspitResponse();

        response.setId(entity.getId());
        response.setPredmetId(entity.getPredmet().getId());
        response.setNastavnikId(entity.getNastavnik().getId());
        response.setDatumOdrzavanja(entity.getDatumOdrzavanja());
        response.setVremePocetka(entity.getVremePocetka());
        response.setZakljucen(entity.getZakljucen());

        return response;
    }

    public List<IspitResponse> toResponseList(List<Ispit> entities){
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
