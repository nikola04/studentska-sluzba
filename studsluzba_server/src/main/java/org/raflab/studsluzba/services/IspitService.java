package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.IspitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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

    private void fetchChilds(Ispit target, Long predmetId, Long nastavnikId, Long ispitniRokId){
        target.setPredmet(predmetService.getPredmet(predmetId));
        target.setNastavnik(nastavnikService.getNastavnik(nastavnikId));
        target.setIspitniRok(ispitniRokService.getIspitniRok(ispitniRokId));
    }

    private void beforeSaveCheck(Ispit ispit){
        IspitniRok ispitniRok = ispit.getIspitniRok();
        LocalDate datumOdrzavanja = ispit.getDatumOdrzavanja();
        LocalDate today = LocalDate.now();

        if(ispitniRok.getKraj().isBefore(today))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IspitniRok has already ended");

        if(datumOdrzavanja.isBefore(ispitniRok.getPocetak()) || datumOdrzavanja.isAfter(ispitniRok.getKraj()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field 'datumOdrzavanja' must be between " + ispitniRok.getPocetak() + " and " + ispitniRok.getKraj());
    }

    @Transactional
    public Ispit saveIspit(Ispit ispit, Long predmetId, Long nastavnikId, Long ispitniRokId){
        this.fetchChilds(ispit, predmetId, nastavnikId, ispitniRokId);
        this.beforeSaveCheck(ispit);
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
        Ispit existing = this.getIspit(id);
        ispitRepository.delete(existing);
    }

    @Transactional
    public Ispit updateIspit(Long id, Ispit ispit, Long predmetId, Long nastavnikId, Long ispitniRokId){
        Ispit existing = this.getIspit(id);

        this.fetchChilds(existing, predmetId, nastavnikId, ispitniRokId);
        this.beforeSaveCheck(existing);

        existing.setDatumOdrzavanja(ispit.getDatumOdrzavanja());
        existing.setVremePocetka(ispit.getVremePocetka());
        existing.setZakljucen(ispit.getZakljucen() != null ? ispit.getZakljucen() : false);

        return ispitRepository.save(existing);
    }

}
