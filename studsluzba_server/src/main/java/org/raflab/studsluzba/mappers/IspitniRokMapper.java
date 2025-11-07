package org.raflab.studsluzba.mappers;

import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.model.IspitniRok;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IspitniRokMapper {
    public IspitniRok toEntity(IspitniRokRequest request){
        IspitniRok rok = new IspitniRok();

        rok.setPocetak(request.getPocetak());
        rok.setKraj(request.getKraj());

        return rok;
    }

    public IspitniRokResponse toResponse(IspitniRok entity){
        IspitniRokResponse response = new IspitniRokResponse();

        response.setPocetak(entity.getPocetak());
        response.setKraj(entity.getKraj());

        return response;
    }

    public List<IspitniRokResponse> toResponseList(List<IspitniRok> entities){
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
