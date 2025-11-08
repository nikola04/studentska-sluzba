package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.repositories.SrednjaSkolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SrednjaSkolaService {
    @Autowired
    private SrednjaSkolaRepository srednjaSkolaRepository;

    public SrednjaSkola getSrednjaSkola(Long id){
        return srednjaSkolaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[SrednjaSkola] Not found: " + id));
    }

    public List<SrednjaSkola> getAllSrednjaSkola(){
        return srednjaSkolaRepository.findAll();
    }

    public SrednjaSkola createSrednjaSkola(SrednjaSkola srednjaSkola){
        return srednjaSkolaRepository.save(srednjaSkola);
    }

    @Transactional
    public void deleteSrednjaSkola(Long id){
        SrednjaSkola existing = this.getSrednjaSkola(id);
        srednjaSkolaRepository.delete(existing);
    }
}
