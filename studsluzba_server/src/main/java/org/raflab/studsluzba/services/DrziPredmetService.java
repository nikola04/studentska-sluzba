package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.DrziPredmet;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.DrziPredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DrziPredmetService {
    @Autowired
    private final DrziPredmetRepository drziPredmetRepository;
    @Autowired
    private NastavnikService nastavnikService;
    @Autowired
    private PredmetService predmetService;
    @Autowired
    private SkolskaGodinaService skolskaGodinaService;

    public DrziPredmet getDrziPredmet(Long id) {
        return drziPredmetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[DrziPredmet] Not found: " + id));
    }

    public List<DrziPredmet> getAllDrziPredmetByNastavnikId(Long nastavnikId) {
        return drziPredmetRepository.getDrziPredmetByNastavnikId(nastavnikId);
    }

    public List<DrziPredmet> getAllDrziPredmetByPredmetIdGodinaId(Long predmetId, Long godinaId){
        return drziPredmetRepository.findByPredmetIdGodinaId(predmetId, godinaId);
    }

    public List<DrziPredmet> getAllDrziPredmetByPredmetId(Long predmetId) {
        return drziPredmetRepository.getDrziPredmetByPredmetId(predmetId);
    }


    @Transactional
    public void saveDrziPredmet(Long nastavnikId, Long predmetId, Long skolskaGodinaId) {
        if (drziPredmetRepository.getDrziPredmetByPredmetIdNastavnikIdSkolskaGodinaId(predmetId, nastavnikId, skolskaGodinaId) != null)
            throw new ResourceAlreadyExistsException("[DrziPredmet] Already exists: " + predmetId + " " + nastavnikId + " " + skolskaGodinaId);

        Nastavnik nastavnik = nastavnikService.getNastavnik(nastavnikId);
        Predmet predmet = predmetService.getPredmet(predmetId);
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);

        DrziPredmet drziPredmet = new DrziPredmet();
        drziPredmet.setNastavnik(nastavnik);
        drziPredmet.setPredmet(predmet);
        drziPredmet.setSkolskaGodina(skolskaGodina);

        drziPredmetRepository.save(drziPredmet);
    }

    @Transactional
    public void deleteDrziPredmet(Long nastavnikId, Long predmetId, Long skolskaGodinaId) {
        DrziPredmet drziPredmet = drziPredmetRepository.getDrziPredmetByPredmetIdNastavnikIdSkolskaGodinaId(predmetId, nastavnikId, skolskaGodinaId);
        drziPredmetRepository.delete(drziPredmet);
    }
}