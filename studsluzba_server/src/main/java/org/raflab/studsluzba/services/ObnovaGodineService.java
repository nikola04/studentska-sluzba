package org.raflab.studsluzba.services;

import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.ObnovaGodineRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.raflab.studsluzba.repositories.UpisGodineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObnovaGodineService {
    @Autowired
    private ObnovaGodineRepository obnovaGodineRepository;
    @Autowired
    private UpisGodineRepository upisGodineRepository;
    @Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    private SkolskaGodinaService skolskaGodinaService;
    @Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private SlusaPredmetService slusaPredmetService;

    public List<ObnovaGodine> getAllObnovljenaGodinaByStudentIndeks(Long studentIndeksId){
        return obnovaGodineRepository.findAllByStudentIndeksId(studentIndeksId);
    }

    public ObnovaGodine saveObnovaGodine(Long studentIndeksId, Long skolskaGodinaId, String napomena){
        SkolskaGodina currentGodina = skolskaGodinaRepository.findByAktivan(true);
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);
        StudentIndeks indeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        UpisGodine latestUpis = upisGodineRepository.findLatestUpisByStudentIndeksId(studentIndeksId);
        ObnovaGodine latestObnova = obnovaGodineRepository.findLatestObnovaByStudentIndeksId(studentIndeksId);

        if(currentGodina != null && currentGodina.getGodina() >= skolskaGodina.getGodina())
            throw new IllegalArgumentException("[ObnovaGodine] Skolska godina must be newer than current");

        if(latestUpis == null)
            throw new IllegalArgumentException("[ObnovaGodine] Student has not been signed yet. Student should sign first year");

        Integer latestGodina = latestUpis.getSkolskaGodina().getGodina();
        if(latestObnova != null && latestObnova.getSkolskaGodina().getGodina() > latestGodina)
            latestGodina = latestObnova.getSkolskaGodina().getGodina();

        if(latestUpis.getSkolskaGodina().getId().equals(skolskaGodina.getId()))
            throw new IllegalArgumentException("[ObnovaGodine] Student already signed in this year");

        if(latestGodina >= skolskaGodina.getGodina())
            throw new IllegalArgumentException("[ObnovaGodine] Student already signed in this year or year passed");

        List<Predmet> nepolozeniPredmeti = predmetRepository.findNepolozeniPredmeti(indeks.getId(), latestUpis.getSkolskaGodina().getId());

        slusaPredmetService.addAllSlusaPredmet(skolskaGodina, indeks, nepolozeniPredmeti);
        ObnovaGodine obnovaGodine = new ObnovaGodine();
        obnovaGodine.setGodina(latestUpis.getGodina());
        obnovaGodine.setDatumObnove(LocalDate.now());
        obnovaGodine.setStudentIndeks(indeks);
        obnovaGodine.setSkolskaGodina(skolskaGodina);
        obnovaGodine.setNapomena(napomena);
        obnovaGodine.setObnovljeniPredmeti(new ArrayList<>(nepolozeniPredmeti));
        return obnovaGodineRepository.save(obnovaGodine);
    }
}