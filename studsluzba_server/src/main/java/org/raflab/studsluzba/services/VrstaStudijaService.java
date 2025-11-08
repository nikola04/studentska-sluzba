package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.VrstaStudija;
import org.raflab.studsluzba.repositories.VrstaStudijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VrstaStudijaService {
    @Autowired
    private VrstaStudijaRepository vrstaStudijaRepository;

    public VrstaStudija getVrstaStudija(Long id){
        return vrstaStudijaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("[VrstaStudija] Not found: " + id));
    }

    public List<VrstaStudija> getAllVrstaStudija(){
        return vrstaStudijaRepository.findAll();
    }

    public VrstaStudija createVrstaStudija(VrstaStudija vrstaStudija){
        return vrstaStudijaRepository.save(vrstaStudija);
    }

    @Transactional
    public void deleteVrstaStudija(Long id){
        VrstaStudija existing = this.getVrstaStudija(id);
        vrstaStudijaRepository.delete(existing);
    }
}
