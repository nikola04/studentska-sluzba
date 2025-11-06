package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.model.StudijskiProgram;
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
    PredmetRepository predmetRepository;
    @Autowired
    StudijskiProgramRepository studijskiProgramRepository;

    @Transactional
    public Predmet savePredmet(Predmet predmet, Long studProgramId) {
        if (studProgramId != null) {
            StudijskiProgram sp = studijskiProgramRepository.findById(studProgramId)
                    .orElseThrow(() -> new ResourceNotFoundException("Studijski program not found: " + studProgramId));
            predmet.setStudProgram(sp);
        }
       return this.predmetRepository.save(predmet);
    }

    public List<Predmet> getAllPredmets() {
        return predmetRepository.findAll();
    }
    public Predmet getPredmetById(Long id){
        return predmetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }

    public void deletePredmet(Long id){
        Predmet existing = predmetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        predmetRepository.delete(existing);
    }

    @Transactional
    public Predmet updatePredmet(Long id, Predmet predmet, Long studProgramId){
        Predmet existing = predmetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        existing.setNaziv(predmet.getNaziv());
        existing.setEspb(predmet.getEspb());
        existing.setObavezan(predmet.getObavezan());
        existing.setOpis(predmet.getOpis());
        existing.setSifra(predmet.getSifra());

        if (studProgramId != null) {
            StudijskiProgram sp = studijskiProgramRepository.findById(studProgramId)
                    .orElseThrow(() -> new ResourceNotFoundException("Studijski program not found: " + studProgramId));
            existing.setStudProgram(sp);
        }

        return predmetRepository.save(existing);
    }

    public List<Predmet> getPredmetForGodinaAkreditacije(Integer godinaAkreditacije){
        return predmetRepository.getPredmetForGodinaAkreditacije(godinaAkreditacije);
    }
}
