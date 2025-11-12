package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.response.UpisGodineResponse;
import org.raflab.studsluzba.model.UpisGodine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpisGodineMapper {
    @Autowired
    SkolskaGodinaMapper skolskaGodinaMapper;
    public UpisGodineResponse toResponse(UpisGodine entity){
        UpisGodineResponse response = new UpisGodineResponse();

        response.setId(entity.getId());
        response.setGodina(entity.getGodina());
        response.setDatumUpisa(entity.getDatumUpisa());
        response.setNapomena(entity.getNapomena());
        response.setSkolskaGodina(skolskaGodinaMapper.toResponse(entity.getSkolskaGodina()));

        return response;
    }

    public List<UpisGodineResponse> toResponseList(List<UpisGodine> upisGodineList){
        return upisGodineList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
