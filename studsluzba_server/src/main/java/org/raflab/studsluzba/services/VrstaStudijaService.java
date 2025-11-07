package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.VrstaStudija;
import org.raflab.studsluzba.repositories.VrstaStudijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VrstaStudijaService {
    @Autowired
    private VrstaStudijaRepository vrstaStudijaRepository;

    public VrstaStudija findVrstaStudijaById(Long id){
        return vrstaStudijaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("[VrstaStudija] Not found: " + id));
    }
}
