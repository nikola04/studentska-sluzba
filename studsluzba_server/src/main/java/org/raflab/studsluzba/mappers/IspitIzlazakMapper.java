package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.IspitIzlazakRequest;
import org.raflab.studsluzba.controllers.response.IspitIzlazakResponse;
import org.raflab.studsluzba.model.IspitIzlazak;
import org.springframework.stereotype.Component;

@Component
public class IspitIzlazakMapper {
    public IspitIzlazak toEntity(IspitIzlazakRequest request) {
        IspitIzlazak entity = new IspitIzlazak();

        entity.setBrojPoena(request.getBrojPoena());
        entity.setPonisten(request.getPonisten());
        entity.setNapomena(request.getNapomena());

        return entity;
    }

    public IspitIzlazakResponse toResponse(IspitIzlazak entity) {
        if (entity == null) return null;
        IspitIzlazakResponse response = new IspitIzlazakResponse();

        response.setId(entity.getId());
        response.setBrojPoena(entity.getBrojPoena());
        response.setPonisten(entity.getPonisten());
        response.setNapomena(entity.getNapomena());

        return response;
    }
}
