package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.model.PredispitnaObaveza;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPredispitnaObaveza;
import org.raflab.studsluzba.repositories.StudentPredispitnaObavezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPredispitnaObavezaService {
    @Autowired
    private StudentPredispitnaObavezaRepository studentPredispitnaObavezaRepository;
    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private PredispitnaObavezaService predispitnaObavezaService;

    public List<StudentPredispitnaObaveza> getAllStudentPredispitnaObaveza(Long studentIndeksId){
        return studentPredispitnaObavezaRepository.findAllByStudentIndeksId(studentIndeksId);
    }

    public List<StudentPredispitnaObaveza> getAllStudentPredispitnaObavezaForPredmetId(Long studentIndeksId, Long predmetId){
        return studentPredispitnaObavezaRepository.findAllByStudentIndeksIdAndPredmetId(studentIndeksId, predmetId);
    }

    public StudentPredispitnaObaveza saveStudentPredispitnaObaveza(StudentPredispitnaObaveza studentPredispitnaObaveza, Long studentIndeksId, Long predispitnaObavezaId){
        StudentIndeks studentIndeks = studentIndeksService.getStudentIndeks(studentIndeksId);
        PredispitnaObaveza predispitnaObaveza = predispitnaObavezaService.getPredispitnaObaveza(predispitnaObavezaId);

        if(studentPredispitnaObavezaRepository.findByStudentIndeksIdAndPredispitnaId(studentIndeksId, predispitnaObavezaId) != null)
            throw new ResourceAlreadyExistsException("[StudentPredispitnaObaveza] You have already signed this predispitna obaveza");

        studentPredispitnaObaveza.setStudentIndeks(studentIndeks);
        studentPredispitnaObaveza.setPredispitnaObaveza(predispitnaObaveza);

        if(studentPredispitnaObaveza.getBrojPoena() > predispitnaObaveza.getMaxBrojPoena())
            throw new IllegalArgumentException("[StudentPredispitnaObaveza] Broj poena cannot be greater than maxBrojPoena: " + predispitnaObaveza.getMaxBrojPoena());

        return studentPredispitnaObavezaRepository.save(studentPredispitnaObaveza);
    }

    public void deleteStudentPredispitnaObaveza(Long studentIndeksId, Long predispitnaObavezaId){
        StudentPredispitnaObaveza predispitnaObaveza = studentPredispitnaObavezaRepository.findByStudentIndeksIdAndPredispitnaId(studentIndeksId, predispitnaObavezaId);
        if(predispitnaObaveza == null)
            throw new ResourceAlreadyExistsException("[StudentPredispitnaObaveza] Not found: " + studentIndeksId + " " + predispitnaObavezaId);

        studentPredispitnaObavezaRepository.delete(predispitnaObaveza);
    }
}
