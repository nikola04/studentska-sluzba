package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.controllers.request.PredispitnaObavezaRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.DrziPredmetResponse;
import org.raflab.studsluzba.controllers.response.PredispitnaObavezaResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.mappers.DrziPredmetMapper;
import org.raflab.studsluzba.mappers.PredispitnaObavezaMapper;
import org.raflab.studsluzba.mappers.PredmetMapper;
import org.raflab.studsluzba.model.PredispitnaObaveza;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.services.DrziPredmetService;
import org.raflab.studsluzba.services.PolozenPredmetService;
import org.raflab.studsluzba.services.PredispitnaObavezaService;
import org.raflab.studsluzba.services.PredmetService;
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
    private DrziPredmetService drziPredmetService;
    @Autowired
    private PredispitnaObavezaService predispitnaObavezaService;
    @Autowired
    private PolozenPredmetService polozenPredmetService;

    @Autowired
    private PredmetMapper predmetMapper;
    @Autowired
    private DrziPredmetMapper drziPredmetMapper;
    @Autowired
    private PredispitnaObavezaMapper predispitnaObavezaMapper;

    /* Predmet */
    @GetMapping(path = "/")
    public List<PredmetResponse> getAllPredmeti() {
        List<Predmet> predmeti = predmetService.getAllPredmets();
        return predmetMapper.toResponseList(predmeti);
    }

    @GetMapping(path = "/{id}")
    public PredmetResponse getPredmetById(@PathVariable Long id) {
        Predmet predmet = predmetService.getPredmet(id);
        return predmetMapper.toResponse(predmet);
    }

    @PostMapping(path = "/")
    public Long createPredmet(@Valid @RequestBody PredmetRequest request) {
        Predmet predmet = predmetMapper.toEntity(request);
        Predmet saved = predmetService.savePredmet(predmet, request.getStudijskiProgramId());
        return saved.getId();
    }

    @PatchMapping(path = "/{id}")
    public PredmetResponse updatePredmet(@PathVariable Long id, @Valid @RequestBody PredmetRequest request) {
        Predmet predmet = predmetMapper.toEntity(request);
        Predmet updated = predmetService.updatePredmet(id, predmet, request.getStudijskiProgramId());
        return predmetMapper.toResponse(updated);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deletePredmet(@PathVariable Long id) {
        predmetService.deletePredmet(id);
        return true;
    }

    @GetMapping(path = "/{id}/prosecna-ocena")
    public Double getProsecnaOcena(@PathVariable Long id, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {
        return polozenPredmetService.getAveragePredmetGradeFromToYear(id, from, to);
    }

    /* DrziPredmet */
    @GetMapping(path = "/{predmetId}/drzi")
    public List<DrziPredmetResponse> getDrziPredmet(@PathVariable Long predmetId) {
        return drziPredmetMapper.toResponseListWithNastavnik(drziPredmetService.getAllDrziPredmetByPredmetId(predmetId));
    }

    /* PredispitnaObaveza */
    @GetMapping(path = "/{predmetId}/predispitna")
    public List<PredispitnaObavezaResponse> getPredispitnaObaveza(@PathVariable Long predmetId) {
        return predispitnaObavezaMapper.toResponseList(predispitnaObavezaService.getAllPredispitnaObavezaForPredmetId(predmetId));
    }

    @PostMapping(path = "/{predmetId}/predispitna")
    public Long addNewPredispitnaObaveza(@PathVariable Long predmetId, @Valid @RequestBody PredispitnaObavezaRequest request) {
        PredispitnaObaveza predispitnaObaveza = predispitnaObavezaMapper.toEntity(request);

        return predispitnaObavezaService.savePredispitnaObaveza(predispitnaObaveza, predmetId, request.getSkolskaGodinaId(), request.getPredispitnaObavezaVrstaId()).getId();
    }

    @PatchMapping(path = "/{predmetId}/predispitna/{id}")
    public PredispitnaObavezaResponse updatePredispitnaObaveza(@PathVariable Long id, @PathVariable Long predmetId, @Valid @RequestBody PredispitnaObavezaRequest request) {
        PredispitnaObaveza predispitnaObaveza = predispitnaObavezaMapper.toEntity(request);

        return predispitnaObavezaMapper.toResponse(predispitnaObavezaService.updatePredispitnaObaveza(id, predmetId, request.getSkolskaGodinaId(), request.getPredispitnaObavezaVrstaId(), predispitnaObaveza));
    }

    @DeleteMapping(path = "/{predmetId}/predispitna/{predispitnaObavezaId}")
    public boolean deletePredispitnaObaveza(@PathVariable Long predmetId, @PathVariable Long predispitnaObavezaId) {
        predispitnaObavezaService.deletePredispitnaObaveza(predmetId, predispitnaObavezaId);
        return true;
    }
}
