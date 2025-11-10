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

    public void saveObnovaGodine(Long studentIndeksId, Long skolskaGodinaId, String napomena){
        SkolskaGodina currentGodina = skolskaGodinaRepository.findByAktivan(true);
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);
        StudentIndeks indeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        UpisGodine latestUpis = upisGodineRepository.findLatestUpisByStudentIndeksId(studentIndeksId);

        if(currentGodina.getGodina() >= skolskaGodina.getGodina())
            throw new IllegalArgumentException("[ObnovaGodine] Skolska godina must be newer than current");

        if(latestUpis == null)
            throw new IllegalArgumentException("[ObnovaGodine] Student has not been signed yet. Student should sign first year");

        if(latestUpis.getSkolskaGodina().getId().equals(currentGodina.getId()))
            throw new IllegalArgumentException("[ObnovaGodine] Student already signed in this year");

        if(obnovaGodineRepository.findObnovaGodineByStudentIndeksIdGodinaId(studentIndeksId, currentGodina.getId()) != null)
            throw new IllegalArgumentException("[ObnovaGodine] Student already signed in this year");

        List<Predmet> nepolozeniPredmeti = predmetRepository.findNepolozeniPredmeti(indeks.getId(), currentGodina.getId());

        slusaPredmetService.addAllSlusaPredmet(skolskaGodina, indeks, nepolozeniPredmeti);
        ObnovaGodine obnovaGodine = new ObnovaGodine();
        obnovaGodine.setGodina(latestUpis.getGodina());
        obnovaGodine.setDatumObnove(LocalDate.now());
        obnovaGodine.setStudentIndeks(indeks);
        obnovaGodine.setSkolskaGodina(skolskaGodina);
        obnovaGodine.setNapomena(napomena);
        obnovaGodine.setObnovljeniPredmeti(new ArrayList<>(nepolozeniPredmeti));
        obnovaGodineRepository.save(obnovaGodine);
    }
}