package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NastavnikMapper {
    @Autowired
    NastavnikZvanjeMapper nastavnikZvanjeMapper;

    public Nastavnik toEntity(NastavnikRequest request) {
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setIme(request.getIme());
        nastavnik.setPrezime(request.getPrezime());
        nastavnik.setSrednjeIme(request.getSrednjeIme());
        nastavnik.setEmail(request.getEmail());
        nastavnik.setBrojTelefona(request.getBrojTelefona());
        nastavnik.setAdresa(request.getAdresa());
        nastavnik.setDatumRodjenja(request.getDatumRodjenja());
        nastavnik.setPol(request.getPol());
        nastavnik.setJmbg(request.getJmbg());

        return nastavnik;
    }

    public NastavnikResponse toResponse(Nastavnik entity) {
        NastavnikResponse response = new NastavnikResponse();
        response.setId(entity.getId());
        response.setIme(entity.getIme());
        response.setPrezime(entity.getPrezime());
        response.setSrednjeIme(entity.getSrednjeIme());
        response.setEmail(entity.getEmail());
        response.setBrojTelefona(entity.getBrojTelefona());
        response.setAdresa(entity.getAdresa());
        response.setDatumRodjenja(entity.getDatumRodjenja());
        response.setPol(entity.getPol());
        response.setJmbg(entity.getJmbg());
        response.setObrazovanja(entity.getObrazovanja());

        Set<NastavnikZvanje> zvanja = entity.getZvanja();
        if (zvanja != null) {
            response.setZvanja(nastavnikZvanjeMapper.toResponseSet(zvanja));
        }

        return response;
    }

    public List<NastavnikResponse> toResponseList(List<Nastavnik> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
