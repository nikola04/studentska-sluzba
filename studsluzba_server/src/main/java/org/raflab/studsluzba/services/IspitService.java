package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.IspitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IspitService {
    @Autowired
    private IspitRepository ispitRepository;
    @Autowired
    private PredmetService predmetService;
    @Autowired
    private NastavnikService nastavnikService;
    @Autowired
    private IspitniRokService ispitniRokService;

    private void addIspitChildClasses(Ispit target, Long predmetId, Long nastavnikId, Long ispitniRokId){
        target.setPredmet(predmetService.getPredmetById(predmetId));
        target.setNastavnik(nastavnikService.getNastavnik(nastavnikId));
        target.setIspitniRok(ispitniRokService.getIspitniRok(ispitniRokId));
    }

    @Transactional
    public Ispit saveIspit(Ispit ispit, Long predmetId, Long nastavnikId, Long ispitniRokId){
        this.addIspitChildClasses(ispit, predmetId, nastavnikId, ispitniRokId);
        return ispitRepository.save(ispit);
    }

    public Ispit getIspit(Long id){
        return ispitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Ispit] Not found: " + id));
    }

    public List<Ispit> getAllIspit(){
        return ispitRepository.findAll();
    }

    @Transactional
    public void deleteIspit(Long id){
        Ispit existing = ispitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Ispit] Not found: " + id));
        ispitRepository.delete(existing);
    }

    @Transactional
    public Ispit updateIspit(Long id, Ispit ispit, Long predmetId, Long nastavnikId, Long ispitniRokId){
        Ispit existing = ispitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Ispit] Not found: " + id));

        this.addIspitChildClasses(existing, predmetId, nastavnikId, ispitniRokId);

        existing.setDatumOdrzavanja(ispit.getDatumOdrzavanja());
        existing.setVremePocetka(ispit.getVremePocetka());
        existing.setZakljucen(ispit.getZakljucen() != null ? ispit.getZakljucen() : false);

        return ispitRepository.save(existing);
    }

}
