package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.mappers.PredmetMapper;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.services.PredmetService;
import org.raflab.studsluzba.services.SkolskaGodinaService;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/predmet")
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    @Autowired
    private PredmetMapper predmetMapper;

    @GetMapping(path = "/")
    public List<PredmetResponse> getAllPredmeti() {
        List<Predmet> predmeti = predmetService.getAllPredmets();
        return predmetMapper.toResponseList(predmeti);
    }

    // GET BY ID
    @GetMapping(path = "/{id}")
    public PredmetResponse getPredmetById(@PathVariable Long id) {
        Predmet predmet = predmetService.getPredmetById(id);
        return predmetMapper.toResponse(predmet);
    }

    // CREATE (POST)
    @PostMapping(path = "/")
    public Long createPredmet(@Valid @RequestBody PredmetRequest request) {
        Predmet predmet = predmetMapper.toEntity(request);
        Predmet saved = predmetService.savePredmet(predmet, request.getStudijskiProgramId());
        return saved.getId();
    }

    // UPDATE (PATCH)
    @PatchMapping(path = "/{id}")
    public PredmetResponse updatePredmet(@PathVariable Long id, @Valid @RequestBody PredmetRequest request) {
        Predmet predmet = predmetMapper.toEntity(request);
        Predmet updated = predmetService.updatePredmet(id, predmet, request.getStudijskiProgramId());
        return predmetMapper.toResponse(updated);
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public boolean deletePredmet(@PathVariable Long id) {
        predmetService.deletePredmet(id);
        return true;
    }

    // CUSTOM: get by godina akreditacije
    @GetMapping(path = "/godina-akreditacije/{godinaAkreditacije}")
    public List<PredmetResponse> getPredmetiForGodinaAkreditacije(@PathVariable Integer godinaAkreditacije) {
        List<Predmet> predmeti = predmetService.getPredmetForGodinaAkreditacije(godinaAkreditacije);
        return predmetMapper.toResponseList(predmeti);
    }
}
