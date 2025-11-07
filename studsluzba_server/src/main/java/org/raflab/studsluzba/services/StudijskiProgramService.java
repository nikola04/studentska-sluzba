package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.model.VrstaStudija;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.raflab.studsluzba.repositories.VrstaStudijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudijskiProgramService {
    @Autowired
    StudijskiProgramRepository studijskiProgramRepository;

    @Autowired
    VrstaStudijaRepository vrstaStudijaRepository;

    @Transactional
    public StudijskiProgram saveStudijskiProgram(StudijskiProgram studijskiProgram, Long vrstaStudijaId) {
        VrstaStudija vrsta = vrstaStudijaRepository.findById(vrstaStudijaId)
                .orElseThrow(() -> new ResourceNotFoundException("[VrstaStudija] Not found: " + vrstaStudijaId));

        studijskiProgram.setVrstaStudija(vrsta);

        return studijskiProgramRepository.save(studijskiProgram);
    }

    public List<StudijskiProgram> getAllStudijskiProgram(){
        return studijskiProgramRepository.findAll();
    }

    @Transactional
    public StudijskiProgram getStudijskiProgram(Long id){
        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudijskiProgram] Not found: " + id));
        studijskiProgram.getVrstaStudija();
        return studijskiProgram;
    }

    @Transactional
    public void deleteStudijskiProgram(Long id){
        StudijskiProgram existing = studijskiProgramRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudijskiProgram] Not found: " + id));
        studijskiProgramRepository.delete(existing);
    }

    @Transactional
    public StudijskiProgram updateStudijskiProgram(Long id, StudijskiProgram studijskiProgram, Long vrstaStudijaId){
        StudijskiProgram existing = studijskiProgramRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudijskiProgram] Not found: " + id));

        VrstaStudija vrsta = vrstaStudijaRepository.findById(vrstaStudijaId)
                .orElseThrow(() -> new ResourceNotFoundException("[VrstaStudija] Not found: " + vrstaStudijaId));

        existing.setNaziv(studijskiProgram.getNaziv());
        existing.setOznaka(studijskiProgram.getOznaka());
        existing.setGodinaAkreditacije(studijskiProgram.getGodinaAkreditacije());
        existing.setZvanje(studijskiProgram.getZvanje());
        existing.setTrajanjeGodina(studijskiProgram.getTrajanjeGodina());
        existing.setTrajanjeSemestara(studijskiProgram.getTrajanjeSemestara());
        existing.setUkupnoEspb(studijskiProgram.getUkupnoEspb());
        existing.setVrstaStudija(vrsta);

        return studijskiProgramRepository.save(existing);
    }
}
