package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.response.IspitRezultatResponse;
import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.mappers.StudentIndeksMapper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.IspitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private IspitIzlazakService ispitIzlazakService;
    @Autowired
    private PredispitnaObavezaService predispitnaObavezaService;
    @Autowired
    private StudentIndeksMapper studentIndeksMapper;

    private void fetchChilds(Ispit target, Long predmetId, Long nastavnikId, Long ispitniRokId){
        target.setPredmet(predmetService.getPredmet(predmetId));
        target.setNastavnik(nastavnikService.getNastavnik(nastavnikId));
        target.setIspitniRok(ispitniRokService.getIspitniRok(ispitniRokId));
    }

    private void beforeSaveCheck(Ispit ispit){
        IspitniRok ispitniRok = ispit.getIspitniRok();
        LocalDate datumOdrzavanja = ispit.getDatumOdrzavanja();
        LocalDate today = LocalDate.now();

        Ispit existing = ispitRepository.findByIspitniRokIdAndPredmetId(ispitniRok.getId(), ispit.getPredmet().getId());
        if(existing != null)
            throw new ResourceAlreadyExistsException("[Ispit] You have already signed this ispit");

        // check if nastavnik DrziPredmet ???

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

    public List<Ispit> getAllIspitByIspitniRok(Long ispitniRokId){
        return ispitRepository.findByIspitniRokId(ispitniRokId);
    }

    public Page<Ispit> getNepolozeniPageByStudentId(Long studentId, Pageable pageable){
        return ispitRepository.findNepolozeniByStudentId(studentId, pageable);
    }

    public Page<Ispit> getPolozeniPageByStudentId(Long studentId, Pageable pageable){
        return ispitRepository.findPolozeniByStudentId(studentId, pageable);
    }

    public Ispit getIspitByPredmetIdIspitniRokId(Long predmetId, Long ispitniRokId){
        Ispit ispit = ispitRepository.findByIspitniRokIdAndPredmetId(ispitniRokId, predmetId);
        if(ispit == null)
            throw new ResourceNotFoundException("[Ispit] Not found: " + predmetId + " " + ispitniRokId);
        return ispit;
    }

    public Double getAverageOcega(Long ispitId){
        return ispitRepository.findAverageOcegaByIspitniRokId(ispitId);
    }

    public List<IspitRezultatResponse> getIspitRezultati(Long ispitId){
        List<IspitIzlazak> izlazakList = ispitIzlazakService.getAllIspitIzlazakByIspitId(ispitId);

        return izlazakList.stream().map(izlazak -> {
            IspitRezultatResponse response = new IspitRezultatResponse();
            StudentIndeks studentIndeks = izlazak.getIspitPrijava().getStudentIndeks();
            Ispit ispit = izlazak.getIspitPrijava().getIspit();
            Double predispitniPoeni = predispitnaObavezaService.getPoeniForStudentIndeksByPredmetId(studentIndeks.getId(), ispit.getPredmet().getId(), ispit.getIspitniRok().getSkolskaGodina().getId());

            response.setBrojPoenaIspit(izlazak.getBrojPoena());
            response.setStudentIndeks(studentIndeksMapper.toResponse(studentIndeks));
            response.setBrojPoenaPredispitne(predispitniPoeni);
            response.setBrojPoenaUkupno(izlazak.getBrojPoena() + predispitniPoeni);

            return response;
        }).collect(Collectors.toList());
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
