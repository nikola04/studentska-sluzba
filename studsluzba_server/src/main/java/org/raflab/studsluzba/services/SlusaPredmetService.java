package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.ObnovaGodineRepository;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.UpisGodineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SlusaPredmetService {
    @Autowired
    ObnovaGodineRepository obnovaGodineRepository;
    @Autowired
    private SlusaPredmetRepository slusaPredmetRepository;
    @Autowired
    private SkolskaGodinaRepository skolskaGodinaRepository;
    @Autowired
    private UpisGodineRepository upisGodineRepository;

    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private DrziPredmetService drziPredmetService;

    public List<PredmetSlusa> getAllSlusaPredmet(Long studentIndeksId){
        return slusaPredmetRepository.findSlusaPredmetByStudentIndeksId(studentIndeksId);
    }

    @Transactional
    public void addAllSlusaPredmet(SkolskaGodina skolskaGodina, StudentIndeks studentIndeks, List<Predmet> predmetList){
        Random random = new Random();
        AtomicBoolean error = new AtomicBoolean(false);
        predmetList.forEach(predmet -> {
            List<DrziPredmet> drziPredmetList = drziPredmetService.getAllDrziPredmetByPredmetIdGodinaId(predmet.getId(), skolskaGodina.getId());
            if(drziPredmetList.isEmpty()){
                error.set(true);
            }else {
                DrziPredmet drziPredmet = drziPredmetList.get(random.nextInt(drziPredmetList.size()));

                PredmetSlusa predmetSlusa = new PredmetSlusa();
                predmetSlusa.setStudentIndeks(studentIndeks);
                predmetSlusa.setDrziPredmet(drziPredmet);

                slusaPredmetRepository.save(predmetSlusa);
            }
        });

        if(error.get())
            throw new IllegalArgumentException("[SlusaPredmet] There are no DrziPredmet for some Predmets in this SkolskaGodina");
    }

    @Transactional
    public PredmetSlusa saveSlusaPredmet(Long studentIndeksId, Long drziPredmetId){
        if (slusaPredmetRepository.findByDrziPredmetIdAndStudentIndeksId(drziPredmetId, studentIndeksId) != null)
            throw new ResourceAlreadyExistsException("[PredmetSlusa] You have already signed this predmet");

        SkolskaGodina currentGodina = skolskaGodinaRepository.findByAktivan(true);
        StudentIndeks studentIndeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        UpisGodine latestUpis = upisGodineRepository.findLatestUpisByStudentIndeksId(studentIndeksId);
        DrziPredmet drziPredmet = drziPredmetService.getDrziPredmet(drziPredmetId);

        int studentGodina = latestUpis.getGodina();
        int predmetGodina = (int) Math.ceil((double) drziPredmet.getPredmet().getSemestar() / 2);

        if(studentGodina != predmetGodina)
            throw new IllegalArgumentException("[PredmetSlusa] Student and predmet must be in the same year");

        Integer espb = slusaPredmetRepository.sumESPBForSlusaPredmetByStudentIdGodinaId(studentIndeksId, latestUpis.getSkolskaGodina().getId());

        if(latestUpis.getSkolskaGodina().getId().equals(currentGodina.getId())) { // nije upisao godinu. Proveriti da li je obnovio. ukoliko nije ni obnovio ne dozvoliti da slusa
            if(obnovaGodineRepository.findObnovaGodineByStudentIndeksIdGodinaId(studentIndeksId, currentGodina.getId()) == null)
                throw new IllegalArgumentException("[PredmetSlusa] You have not signed this predmet");
            if (drziPredmet.getPredmet().getEspb() + espb > 60) {
                throw new IllegalArgumentException("[PredmetSlusa] You have exceeded the limit of ESPB: 60");
            }
        }

        PredmetSlusa predmetSlusa = new PredmetSlusa();
        predmetSlusa.setStudentIndeks(studentIndeks);
        predmetSlusa.setDrziPredmet(drziPredmet);

        return slusaPredmetRepository.save(predmetSlusa);
    }

    @Transactional
    public void deleteSlusaPredmet(Long studentIndeksId, Long drziPredmetId){
        PredmetSlusa predmetSlusa = slusaPredmetRepository.findByDrziPredmetIdAndStudentIndeksId(drziPredmetId, studentIndeksId);
        if (predmetSlusa == null)
            throw new ResourceAlreadyExistsException("[PredmetSlusa] You have not signed this predmet");

        slusaPredmetRepository.delete(predmetSlusa);
    }
}
