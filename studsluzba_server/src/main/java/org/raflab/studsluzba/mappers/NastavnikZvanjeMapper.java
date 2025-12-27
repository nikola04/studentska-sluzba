package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.controllers.response.NastavnikZvanjeResponse;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.raflab.studsluzba.model.NaucnaOblast;
import org.raflab.studsluzba.model.UzaNaucnaOblast;
import org.raflab.studsluzba.model.Zvanje;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NastavnikZvanjeMapper {

    public NastavnikZvanje toEntity(NastavnikZvanjeRequest request) {
        NastavnikZvanje nastavnikZvanje = new NastavnikZvanje();

        Zvanje zvanje = new Zvanje();
        zvanje.setId(request.getZvanjeId());
        nastavnikZvanje.setZvanje(zvanje);

        NaucnaOblast naucnaOblast = new NaucnaOblast();
        naucnaOblast.setId(request.getNaucnaOblastId());
        nastavnikZvanje.setNaucnaOblast(naucnaOblast);

        UzaNaucnaOblast uzaNaucnaOblast = new UzaNaucnaOblast();
        uzaNaucnaOblast.setId(request.getUzaNaucnaOblastId());
        nastavnikZvanje.setUzaNaucnaOblast(uzaNaucnaOblast);

        nastavnikZvanje.setDatumIzbora(request.getDatumIzbora());
        nastavnikZvanje.setAktivno(request.getAktivno() != null ? request.getAktivno() : true); // default true
        return nastavnikZvanje;
    }

    public Set<NastavnikZvanje> toEntitySet(Set<NastavnikZvanjeRequest> requests) {
        return requests.stream().map(this::toEntity).collect(java.util.stream.Collectors.toSet());
    }

    public NastavnikZvanjeResponse toResponse(NastavnikZvanje entity) {
        NastavnikZvanjeResponse response = new NastavnikZvanjeResponse();
        response.setId(entity.getId());
        response.setDatumIzbora(entity.getDatumIzbora());
        response.setNaucnaOblast(entity.getNaucnaOblast());
        response.setUzaNaucnaOblast(entity.getUzaNaucnaOblast());
        response.setZvanje(entity.getZvanje());
        response.setAktivno(entity.isAktivno());
        return response;
    }

    public Set<NastavnikZvanjeResponse> toResponseSet(Set<NastavnikZvanje> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toSet());
    }
}
