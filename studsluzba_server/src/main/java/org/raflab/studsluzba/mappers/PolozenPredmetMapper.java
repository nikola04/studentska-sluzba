package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.PolozenPredmetRequest;
import org.raflab.studsluzba.controllers.response.PolozenPredmetResponse;
import org.raflab.studsluzba.model.PolozenPredmet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolozenPredmetMapper {
    @Autowired
    private PredmetMapper predmetMapper;
    @Autowired
    private IspitIzlazakMapper ispitIzlazakMapper;

    public PolozenPredmet toEntity(PolozenPredmetRequest request){
        PolozenPredmet entity = new PolozenPredmet();

        entity.setOcena(request.getOcena());

        return entity;
    }

    public PolozenPredmetResponse toResponse(PolozenPredmet entity) {
        PolozenPredmetResponse response = new PolozenPredmetResponse();

        response.setId(entity.getId());
        response.setOcena(entity.getOcena());
        response.setPredmet(predmetMapper.toResponse(entity.getPredmet()));
        response.setIspitIzlazak(ispitIzlazakMapper.toResponse(entity.getIspitIzlazak()));

        return response;
    }

    public List<PolozenPredmetResponse> toResponseList(List<PolozenPredmet> entities) {
        return entities.stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());
    }
}
