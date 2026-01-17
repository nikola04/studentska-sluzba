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
    @Autowired
    private StudentIndeksMapper studentIndeksMapper;

    public IspitPrijavaResponse toResponse(IspitPrijava ispitPrijava, boolean ispitIzlazak) {
        IspitPrijavaResponse response = new IspitPrijavaResponse();

        response.setId(ispitPrijava.getId());
        response.setStudentIndeks(studentIndeksMapper.toResponse(ispitPrijava.getStudentIndeks()));
        response.setDatumPrijave(ispitPrijava.getDatumPrijave());
        response.setIspit(ispitMapper.toResponse(ispitPrijava.getIspit()));
        if (ispitIzlazak)
            response.setIspitIzlazak(ispitIzlazakMapper.toResponse(ispitPrijava.getIspitIzlazak()));
        else response.setIspitIzlazak(null);

        return response;
    }

    public IspitPrijavaResponse toResponse(IspitPrijava ispitPrijava) {
        return this.toResponse(ispitPrijava, true);
    }

    public List<IspitPrijavaResponse> toResponseList(List<IspitPrijava> ispitPrijavaList) {
        return ispitPrijavaList.stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());
    }
}
