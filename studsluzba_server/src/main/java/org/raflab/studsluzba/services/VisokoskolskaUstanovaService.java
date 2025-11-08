package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.VisokoskolskaUstanova;
import org.raflab.studsluzba.repositories.VisokoskolskaUstanovaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VisokoskolskaUstanovaService {
    @Autowired
    private VisokoskolskaUstanovaRepository visokoskolskaUstanovaRepository;

    public VisokoskolskaUstanova getVisokoskolskaUstanova(Long id){
        return visokoskolskaUstanovaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[VisokoskolskaUstanova] Not found: " + id));
    }

    public List<VisokoskolskaUstanova> getAllVisokoskolskaUstanova(){
        return visokoskolskaUstanovaRepository.findAll();
    }

    public VisokoskolskaUstanova createVisokoskolskaUstanova(VisokoskolskaUstanova visokoskolskaUstanova){
        return visokoskolskaUstanovaRepository.save(visokoskolskaUstanova);
    }

    @Transactional
    public void deleteVisokoskolskaUstanova(Long id){
        VisokoskolskaUstanova existing = this.getVisokoskolskaUstanova(id);
        visokoskolskaUstanovaRepository.delete(existing);
    }
}
