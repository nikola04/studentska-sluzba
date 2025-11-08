package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.response.DrziPredmetResponse;
import org.raflab.studsluzba.model.DrziPredmet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrziPredmetMapper {
    @Autowired
    private PredmetMapper predmetMapper;
    @Autowired
    private NastavnikMapper nastavnikMapper;

    public DrziPredmetResponse toResponse(DrziPredmet drziPredmet) {
        DrziPredmetResponse response = new DrziPredmetResponse();

        response.setId(drziPredmet.getId());
        response.setSkolskaGodinaId(drziPredmet.getSkolskaGodina().getId());
        response.setNastavnikId(drziPredmet.getNastavnik().getId());
        response.setPredmetId(drziPredmet.getPredmet().getId());

        return response;
    }

    public DrziPredmetResponse toResponseWithPredmet(DrziPredmet drziPredmet) {
        DrziPredmetResponse response = toResponse(drziPredmet);
        if(drziPredmet.getPredmet() != null)
            response.setPredmet(predmetMapper.toResponse(drziPredmet.getPredmet()));
        return response;
    }

    public DrziPredmetResponse toResponseWithNastavnik(DrziPredmet drziPredmet) {
        DrziPredmetResponse response = toResponse(drziPredmet);
        if(drziPredmet.getPredmet() != null)
            response.setNastavnik(nastavnikMapper.toResponse(drziPredmet.getNastavnik()));
        return response;
    }

    public DrziPredmetResponse toResponseWithNastavnikAndPredmet(DrziPredmet drziPredmet) {
        DrziPredmetResponse response = toResponseWithNastavnik(drziPredmet);
        if(drziPredmet.getPredmet() != null)
            response.setPredmet(predmetMapper.toResponse(drziPredmet.getPredmet()));

        return response;
    }

    public List<DrziPredmetResponse> toResponseList(List<DrziPredmet> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<DrziPredmetResponse> toResponseListWithPredmet(List<DrziPredmet> entities) {
        return entities.stream().map(this::toResponseWithPredmet).collect(Collectors.toList());
    }
    public List<DrziPredmetResponse> toResponseListWithNastavnik(List<DrziPredmet> entities) {
        return entities.stream().map(this::toResponseWithNastavnik).collect(Collectors.toList());
    }
}
