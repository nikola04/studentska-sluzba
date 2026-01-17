package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.IspitIzlazakRequest;
import org.raflab.studsluzba.controllers.response.IspitIzlazakResponse;
import org.raflab.studsluzba.model.IspitIzlazak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IspitIzlazakMapper {
    @Autowired
    private IspitPrijavaMapper ispitPrijavaMapper;

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
        response.setIspitPrijava(ispitPrijavaMapper.toResponse(entity.getIspitPrijava(), false));

        return response;
    }

    public List<IspitIzlazakResponse> toResponseList(List<IspitIzlazak> entityList) {
        return entityList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
