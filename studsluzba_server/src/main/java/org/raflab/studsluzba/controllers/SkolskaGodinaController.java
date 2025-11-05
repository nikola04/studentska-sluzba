package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.services.SkolskaGodinaService;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/api/skolskagodina")
public class SkolskaGodinaController {
    @Autowired
    SkolskaGodinaService skolskaGodinaService;

    @PostMapping(path="/")
    public Long createSkolskaGodina(@RequestBody SkolskaGodinaRequest request) {
        SkolskaGodina skolskaGodina = skolskaGodinaService.saveSkolskaGodina(Converters.toSkolskaGodina(request));
        return skolskaGodina.getId();
    }

    @GetMapping(path = "/")
    public List<SkolskaGodinaResponse> getAllSkolskaGodina(){
        List<SkolskaGodina> skolskaGodinaList = skolskaGodinaService.getAllSkolskaGodina();
        return Converters.toSkolskaGodinaResponseList(skolskaGodinaList);
    }

    @GetMapping(path = "/{id}")
    public SkolskaGodinaResponse getSkolskaGodina(@PathVariable Long id){
        return Converters.toSkolskaGodinaResponse(skolskaGodinaService.getSkolskaGodina(id));
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteSkolskaGodina(@PathVariable Long id){
        skolskaGodinaService.deleteSkolskaGodina(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public SkolskaGodinaResponse updateSkolskaGodina(@PathVariable Long id, @Valid @RequestBody SkolskaGodinaRequest request){
        return Converters.toSkolskaGodinaResponse(skolskaGodinaService.updateSkolskaGodina(id, Converters.toSkolskaGodina(request)));
    }
}
