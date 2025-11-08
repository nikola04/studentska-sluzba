package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.response.SlusaPredmetResponse;
import org.raflab.studsluzba.model.PredmetSlusa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlusaPredmetMapper {
    @Autowired
    DrziPredmetMapper drziPredmetMapper;

    public SlusaPredmetResponse toResponse(PredmetSlusa predmetSlusa) {
        SlusaPredmetResponse slusaPredmetResponse = new SlusaPredmetResponse();

        slusaPredmetResponse.setId(predmetSlusa.getId());
        slusaPredmetResponse.setStudentIndeksId(predmetSlusa.getStudentIndeks().getId());
        slusaPredmetResponse.setDrziPredmet(drziPredmetMapper.toResponseWithNastavnikAndPredmet(predmetSlusa.getDrziPredmet()));

        return slusaPredmetResponse;
    }

    public List<SlusaPredmetResponse> toResponseList(List<PredmetSlusa> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
