package org.raflab.studsluzba.services;

import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpisGodineService {
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

    public void saveUpisGodine(Long studentIndeksId, Long skolskaGodinaId, String napomena){
        SkolskaGodina currentGodina = skolskaGodinaRepository.findByAktivan(true);
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);
        StudentIndeks indeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        UpisGodine latestUpis = upisGodineRepository.findLatestUpisByStudentIndeksId(studentIndeksId);

        if(currentGodina.getGodina() >= skolskaGodina.getGodina())
            throw new IllegalArgumentException("[UpisGodine] Skolska godina must be newer than current");

        if(latestUpis == null){ // first upis
            UpisGodine upisGodine = new UpisGodine();
            upisGodine.setGodina(1);
            upisGodine.setDatumUpisa(LocalDate.now());
            upisGodine.setStudentIndeks(indeks);
            upisGodine.setSkolskaGodina(skolskaGodina);
            upisGodine.setNapomena(napomena);
            upisGodineRepository.save(upisGodine);
            return;
        }

        if(latestUpis.getSkolskaGodina().getId().equals(skolskaGodina.getId())){
            throw new IllegalArgumentException("[UpisGodine] Student already signed in this year");
        }

        Integer espb = indeks.getOstvarenoEspb();
        if(espb < 60 * indeks.getGodina() - 30)
            throw new IllegalArgumentException("[UpisGodine] Not enough ESPB");

        List<Predmet> nepolozeniPredmeti = predmetRepository.findNepolozeniPredmeti(indeks.getId(), currentGodina.getId());

        slusaPredmetService.addAllSlusaPredmet(skolskaGodina, indeks, nepolozeniPredmeti);
        UpisGodine upisGodine = new UpisGodine();
        upisGodine.setGodina(latestUpis.getGodina() + 1);
        upisGodine.setDatumUpisa(LocalDate.now());
        upisGodine.setStudentIndeks(indeks);
        upisGodine.setSkolskaGodina(skolskaGodina);
        upisGodine.setNapomena(napomena);
        upisGodine.setPrenetiPredmeti(new ArrayList<>(nepolozeniPredmeti));
        upisGodineRepository.save(upisGodine);
    }
}
