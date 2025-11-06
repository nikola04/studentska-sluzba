package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SkolskaGodinaService {
    @Autowired
    SkolskaGodinaRepository skolskaGodinaRepo;

    private void onActiveSkolskaGodina(SkolskaGodina skolskaGodina){
        if(skolskaGodina.getAktivan()){
            skolskaGodinaRepo.deactivateSkolskaGodina();
        }
    }

    public SkolskaGodina saveSkolskaGodina(SkolskaGodina skolskaGodina) {
        this.onActiveSkolskaGodina(skolskaGodina);
        return skolskaGodinaRepo.save(skolskaGodina);
    }

    public List<SkolskaGodina> getAllSkolskaGodina(){
        return skolskaGodinaRepo.findAll();
    }

    public SkolskaGodina getSkolskaGodina(Long id){
        return skolskaGodinaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }

    public void deleteSkolskaGodina(Long id){
        SkolskaGodina existing = skolskaGodinaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        skolskaGodinaRepo.delete(existing);
    }

    @Transactional
    public SkolskaGodina updateSkolskaGodina(Long id, SkolskaGodina skolskaGodina){
        SkolskaGodina existing = skolskaGodinaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        this.onActiveSkolskaGodina(skolskaGodina);

        existing.setAktivan(skolskaGodina.getAktivan());

        return skolskaGodinaRepo.save(existing);
    }
}
