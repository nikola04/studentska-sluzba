package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.PolozenPredmetRepository;
import org.raflab.studsluzba.utils.IspitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PolozenPredmetService {
    @Autowired
    private PolozenPredmetRepository polozenPredmetRepository;

    @Autowired
    private IspitIzlazakService ispitIzlazakService;
    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private PredmetService predmetService;
    @Autowired
    private StudentPredispitnaObavezaService studPredObavezaService;

    public List<PolozenPredmet> getAllPolozenPredmet(Long studentIndeksId){
        return polozenPredmetRepository.findByStudentIndeksId(studentIndeksId);
    }

    @Transactional
    public PolozenPredmet savePolozenPredmet(PolozenPredmet polozenPredmet, Long studentIndeksId, Long predmetId, Long ispitIzlazakId){
        StudentIndeks studentIndeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        Predmet predmet = predmetService.getPredmet(predmetId);
        IspitIzlazak ispitIzlazak = null;

        polozenPredmet.setStudentIndeks(studentIndeks);
        polozenPredmet.setPredmet(predmet);

        if(ispitIzlazakId == null){
            polozenPredmet.setIspitIzlazak(null);
        } else {
            ispitIzlazak = ispitIzlazakService.getIspitIzlazak(ispitIzlazakId);
            if(!ispitIzlazak.getIspitPrijava().getStudentIndeks().getId().equals(studentIndeksId) || !ispitIzlazak.getIspitPrijava().getIspit().getPredmet().getId().equals(predmetId))
                throw new ResourceNotFoundException("[PolozenPredmet] IspitIzlazak not valid");
            if(ispitIzlazak.getPonisten())
                throw new ResourceAlreadyExistsException("[PolozenPredmet] IspitIzlazak is canceled");
            if(ispitIzlazak.getPolozenPredmet() != null)
                throw new ResourceAlreadyExistsException("[PolozenPredmet] IspitIzlazak is already passed");

            List<StudentPredispitnaObaveza> studentPredispitnaObavezaList = studPredObavezaService.getAllStudentPredispitnaObavezaForPredmetId(studentIndeksId, predmetId);
            double predispitnePoints = studentPredispitnaObavezaList.stream().mapToDouble(StudentPredispitnaObaveza::getBrojPoena).sum();

            polozenPredmet.setOcena(IspitUtils.calculateOcena(ispitIzlazak.getBrojPoena() + predispitnePoints));
            polozenPredmet.setIspitIzlazak(ispitIzlazak);
        }

        if(polozenPredmet.getOcena() == null || polozenPredmet.getOcena() < 0 || polozenPredmet.getOcena() > 10)
            throw new IllegalArgumentException("[PolozenPredmet] Invalid ocena: " + polozenPredmet.getOcena());
        if(polozenPredmet.getOcena() <= 5)
            throw new ResourceAlreadyExistsException("[PolozenPredmet] Student has not passed exam");

        if(ispitIzlazak != null)
            ispitIzlazak.setPolozenPredmet(polozenPredmet);

        return polozenPredmetRepository.save(polozenPredmet);
    }

    @Transactional
    public void deletePolozenPredmet(Long studentIndeksId, Long predmetId){
        PolozenPredmet predmet = polozenPredmetRepository.findByStudentIndeksIdPredmetId(studentIndeksId, predmetId);
        if(predmet == null)
            throw new ResourceNotFoundException("[PolozenPredmet] Not found: " + studentIndeksId + " " + predmetId);

        predmet.getIspitIzlazak().setPolozenPredmet(null);
        predmet.setIspitIzlazak(null);
        polozenPredmetRepository.delete(predmet);
    }
}
