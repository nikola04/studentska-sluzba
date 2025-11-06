package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.controllers.response.NastavnikZvanjeResponse;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NastavnikZvanjeMapper {

    public NastavnikZvanje toEntity(NastavnikZvanjeRequest request) {
        NastavnikZvanje zvanje = new NastavnikZvanje();
        zvanje.setDatumIzbora(request.getDatumIzbora());
        zvanje.setNaucnaOblast(request.getNaucnaOblast());
        zvanje.setUzaNaucnaOblast(request.getUzaNaucnaOblast());
        zvanje.setZvanje(request.getZvanje());
        zvanje.setAktivno(request.getAktivno() != null ? request.getAktivno() : true); // default true
        return zvanje;
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
