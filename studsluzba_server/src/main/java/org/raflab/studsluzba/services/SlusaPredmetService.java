package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.model.DrziPredmet;
import org.raflab.studsluzba.model.PredmetSlusa;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SlusaPredmetService {
    @Autowired
    private SlusaPredmetRepository slusaPredmetRepository;

    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private DrziPredmetService drziPredmetService;

    public List<PredmetSlusa> getAllSlusaPredmet(Long studentIndeksId){
        return slusaPredmetRepository.findSlusaPredmetByStudentIndeksId(studentIndeksId);
    }

    @Transactional
    public PredmetSlusa saveSlusaPredmet(Long studentIndeksId, Long drziPredmetId){
        if (slusaPredmetRepository.findByDrziPredmetIdAndStudentIndeksId(drziPredmetId, studentIndeksId) != null)
            throw new ResourceAlreadyExistsException("[PredmetSlusa] You have already signed this predmet");

        StudentIndeks studentIndeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        DrziPredmet drziPredmet = drziPredmetService.getDrziPredmet(drziPredmetId);

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
