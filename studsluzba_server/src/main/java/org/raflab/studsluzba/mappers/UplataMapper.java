package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.UplataRequest;
import org.raflab.studsluzba.controllers.response.UplataResponse;
import org.raflab.studsluzba.model.Uplata;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UplataMapper {
    public Uplata toEntity(UplataRequest request) {
        Uplata uplata = new Uplata();

        uplata.setIznosDinari(request.getIznos());

        return uplata;
    }

    public UplataResponse toResponse(Uplata entity) {
        UplataResponse response = new UplataResponse();

        response.setId(entity.getId());
        response.setStudentIndeksId(entity.getStudentIndeks().getId());
        response.setIznos(entity.getIznosDinari());
        response.setKurs(entity.getKurs());
        response.setDatum(entity.getDatumUplate());

        return response;
    }

    public List<UplataResponse> toResponseList(List<Uplata> uplate) {
        return uplate.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
