package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.mappers.SkolskaGodinaMapper;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.services.SkolskaGodinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/api/skolskagodina")
public class SkolskaGodinaController {
    @Autowired
    private SkolskaGodinaMapper skolskaGodinaMapper;
    @Autowired
    private SkolskaGodinaService skolskaGodinaService;

    @PostMapping(path="/")
    public Long createSkolskaGodina(@Valid @RequestBody SkolskaGodinaRequest request) {
        SkolskaGodina skolskaGodina = skolskaGodinaService.saveSkolskaGodina(skolskaGodinaMapper.toEntity(request));
        return skolskaGodina.getId();
    }

    @GetMapping(path = "/")
    public List<SkolskaGodinaResponse> getAllSkolskaGodina(){
        List<SkolskaGodina> skolskaGodinaList = skolskaGodinaService.getAllSkolskaGodina();
        return skolskaGodinaMapper.toResponseList(skolskaGodinaList);
    }

    @GetMapping(path = "/{id}")
    public SkolskaGodinaResponse getSkolskaGodina(@PathVariable Long id){
        return skolskaGodinaMapper.toResponse(skolskaGodinaService.getSkolskaGodina(id));
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteSkolskaGodina(@PathVariable Long id){
        skolskaGodinaService.deleteSkolskaGodina(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public SkolskaGodinaResponse updateSkolskaGodina(@PathVariable Long id, @Valid @RequestBody SkolskaGodinaRequest request){
        SkolskaGodina sgEntity = skolskaGodinaMapper.toEntity(request);
        return skolskaGodinaMapper.toResponse(skolskaGodinaService.updateSkolskaGodina(id, sgEntity));
    }
}
