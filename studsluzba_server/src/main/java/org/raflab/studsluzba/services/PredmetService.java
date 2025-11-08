package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

public class PredmetService {
    @Autowired
    private PredmetRepository predmetRepository;
    @Autowired
    private StudijskiProgramService studijskiProgramService;

    @Transactional
    public Predmet savePredmet(Predmet predmet, Long studProgramId) {
        StudijskiProgram sp = studijskiProgramService.getStudijskiProgram(studProgramId);
        predmet.setStudProgram(sp);
        return this.predmetRepository.save(predmet);
    }

    public List<Predmet> getAllPredmets() {
        return predmetRepository.findAll();
    }
    public Predmet getPredmet(Long id){
        return predmetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Predmet] Not found: " + id));
    }

    @Transactional
    public void deletePredmet(Long id){
        Predmet predmet = this.getPredmet(id);

        for (Grupa g : predmet.getGrupe()) {
            g.getPredmeti().remove(predmet);
        }
        predmet.getGrupe().clear();

        predmetRepository.delete(predmet);
    }

    @Transactional
    public Predmet updatePredmet(Long id, Predmet predmet, Long studProgramId){
        Predmet existing = this.getPredmet(id);

        existing.setNaziv(predmet.getNaziv());
        existing.setEspb(predmet.getEspb());
        existing.setObavezan(predmet.getObavezan());
        existing.setOpis(predmet.getOpis());
        existing.setSifra(predmet.getSifra());

        StudijskiProgram sp = studijskiProgramService.getStudijskiProgram(studProgramId);
        existing.setStudProgram(sp);

        return predmetRepository.save(existing);
    }

    public List<Predmet> getPredmetForGodinaAkreditacije(Integer godinaAkreditacije){
        return predmetRepository.getPredmetForGodinaAkreditacije(godinaAkreditacije);
    }

    public List<Predmet> getPredmetForStudijskiProgram(Long id){
        return predmetRepository.findPredmetForStudijskiProgram(id);
    }

    public Long countByStudijskiProgramId(Long id){
        return predmetRepository.countByStudijskiProgramId(id);
    }
}
