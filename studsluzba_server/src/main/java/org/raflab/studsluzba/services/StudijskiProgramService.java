package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudijskiProgramService {
    @Autowired
    StudijskiProgramRepository studijskiProgramRepository;

    public StudijskiProgram saveStudijskiProgram(StudijskiProgram studijskiProgram) {
        return studijskiProgramRepository.save(studijskiProgram);
    }

    public List<StudijskiProgram> getAllStudijskiProgram(){
        return studijskiProgramRepository.findAll();
    }

    public StudijskiProgram getStudijskiProgram(Long id){
        return studijskiProgramRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudijskiProgram] Not found: " + id));
    }

    @Transactional
    public void deleteStudijskiProgram(Long id){
        StudijskiProgram existing = studijskiProgramRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudijskiProgram] Not found: " + id));
        studijskiProgramRepository.delete(existing);
    }

    @Transactional
    public StudijskiProgram updateStudijskiProgram(Long id, StudijskiProgram studijskiProgram){
        StudijskiProgram existing = studijskiProgramRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudijskiProgram] Not found: " + id));

        existing.setNaziv(studijskiProgram.getNaziv());
        existing.setOznaka(studijskiProgram.getOznaka());
        existing.setGodinaAkreditacije(studijskiProgram.getGodinaAkreditacije());
        existing.setZvanje(studijskiProgram.getZvanje());
        existing.setTrajanjeGodina(studijskiProgram.getTrajanjeGodina());
        existing.setTrajanjeSemestara(studijskiProgram.getTrajanjeSemestara());
        existing.setUkupnoEspb(studijskiProgram.getUkupnoEspb());
        existing.setVrstaStudija(studijskiProgram.getVrstaStudija());

        return studijskiProgramRepository.save(existing);
    }
}
