package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.Predmet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PredmetMapper {
    @Autowired
    StudijskiProgramMapper studijskiProgramMapper;

    public Predmet toEntity(PredmetRequest request) {
        Predmet p = new Predmet();
        p.setNaziv(request.getNaziv());
        p.setEspb(request.getEspb());
        p.setObavezan(request.getObavezan());
        p.setOpis(request.getOpis());
        p.setSifra(request.getSifra());
        return p;
    }

    public PredmetResponse toResponse(Predmet entity) {
        PredmetResponse r = new PredmetResponse();
        r.setId(entity.getId());
        r.setNaziv(entity.getNaziv());
        r.setEspb(entity.getEspb());
        r.setObavezan(entity.getObavezan());
        r.setOpis(entity.getOpis());
        r.setSifra(entity.getSifra());
        r.setStudijskiProgram(studijskiProgramMapper.toResponse(entity.getStudProgram()));
        return r;
    }

    public List<PredmetResponse> toResponseList(List<Predmet> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
