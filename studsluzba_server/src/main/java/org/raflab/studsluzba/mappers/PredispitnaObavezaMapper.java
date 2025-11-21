package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.PredispitnaObavezaRequest;
import org.raflab.studsluzba.controllers.response.PredispitnaObavezaResponse;
import org.raflab.studsluzba.model.PredispitnaObaveza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PredispitnaObavezaMapper {
    @Autowired
    private SkolskaGodinaMapper skolskaGodinaMapper;

    public PredispitnaObaveza toEntity(PredispitnaObavezaRequest request) {
        PredispitnaObaveza predispitnaObaveza = new PredispitnaObaveza();

        predispitnaObaveza.setMaxBrojPoena(request.getMaxBrojPoena());

        return predispitnaObaveza;
    }

    public PredispitnaObavezaResponse toResponse(PredispitnaObaveza entity) {
        PredispitnaObavezaResponse response = new PredispitnaObavezaResponse();

        response.setId(entity.getId());
        response.setPredmetId(entity.getPredmet().getId());
        response.setVrsta(entity.getVrsta());
        response.setMaxBrojPoena(entity.getMaxBrojPoena());
        response.setSkolskaGodina(skolskaGodinaMapper.toResponse(entity.getSkolskaGodina()));

        return response;
    }

    public List<PredispitnaObavezaResponse> toResponseList(List<PredispitnaObaveza> predispitnaObavezaList) {
        return predispitnaObavezaList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
