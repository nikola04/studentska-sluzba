package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
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
    private SkolskaGodinaRepository skolskaGodinaRepo;

    private void beforeSaveCheck(SkolskaGodina skolskaGodina){
        if(skolskaGodina.getAktivan()){
            skolskaGodinaRepo.deactivateSkolskaGodina();
        }

        if(this.skolskaGodinaRepo.findByGodina(skolskaGodina.getGodina()) != null)
            throw new ResourceAlreadyExistsException("[SkolskaGodina] Godina already exists: " + skolskaGodina.getGodina());
    }

    public SkolskaGodina saveSkolskaGodina(SkolskaGodina skolskaGodina) {
        this.beforeSaveCheck(skolskaGodina);
        return skolskaGodinaRepo.save(skolskaGodina);
    }

    public List<SkolskaGodina> getAllSkolskaGodina(){
        return skolskaGodinaRepo.findAll();
    }

    public SkolskaGodina getSkolskaGodina(Long id){
        return skolskaGodinaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("[SkolskaGodina] Not found: " + id));
    }

    @Transactional
    public void deleteSkolskaGodina(Long id){
        SkolskaGodina existing = this.getSkolskaGodina(id);
        skolskaGodinaRepo.delete(existing);
    }

    @Transactional
    public SkolskaGodina updateSkolskaGodina(Long id, SkolskaGodina skolskaGodina){
        SkolskaGodina existing = this.getSkolskaGodina(id);

        this.beforeSaveCheck(skolskaGodina);

        existing.setAktivan(skolskaGodina.getAktivan());
        existing.setGodina(skolskaGodina.getGodina());

        return skolskaGodinaRepo.save(existing);
    }
}
