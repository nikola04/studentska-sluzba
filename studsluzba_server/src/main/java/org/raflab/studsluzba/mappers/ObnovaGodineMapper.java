package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.response.ObnovaGodineResponse;
import org.raflab.studsluzba.model.ObnovaGodine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObnovaGodineMapper {
    @Autowired
    SkolskaGodinaMapper skolskaGodinaMapper;
    public ObnovaGodineResponse toResponse(ObnovaGodine entity){
        ObnovaGodineResponse response = new ObnovaGodineResponse();

        response.setId(entity.getId());
        response.setGodina(entity.getGodina());
        response.setDatumObnove(entity.getDatumObnove());
        response.setNapomena(entity.getNapomena());
        response.setSkolskaGodina(skolskaGodinaMapper.toResponse(entity.getSkolskaGodina()));

        return response;
    }

    public List<ObnovaGodineResponse> toResponseList(List<ObnovaGodine> obnovaGodineList){
        return obnovaGodineList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
