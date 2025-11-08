package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.response.IspitPrijavaResponse;
import org.raflab.studsluzba.model.IspitPrijava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IspitPrijavaMapper {
    @Autowired
    private IspitMapper ispitMapper;
    @Autowired
    private IspitIzlazakMapper ispitIzlazakMapper;

    public IspitPrijavaResponse toResponse(IspitPrijava ispitPrijava) {
        IspitPrijavaResponse response = new IspitPrijavaResponse();

        response.setId(ispitPrijava.getId());
        response.setStudentIndeksId(ispitPrijava.getStudentIndeks().getId());
        response.setDatumPrijave(ispitPrijava.getDatumPrijave());
        response.setIspit(ispitMapper.toResponse(ispitPrijava.getIspit()));
        response.setIspitIzlazak(ispitIzlazakMapper.toResponse(ispitPrijava.getIspitIzlazak()));

        return response;
    }

    public List<IspitPrijavaResponse> toResponseList(List<IspitPrijava> ispitPrijavaList) {
        return ispitPrijavaList.stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());
    }
}
