package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.VisokoskolskaUstanova;
import org.raflab.studsluzba.repositories.VisokoskolskaUstanovaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisokoskolskaUstanovaService {
    @Autowired
    private VisokoskolskaUstanovaRepository visokoskolskaUstanovaRepository;

    public VisokoskolskaUstanova getVisokoskolskaUstanovaById(Long id){
        return visokoskolskaUstanovaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[VisokoskolskaUstanova] Not found: " + id));
    }
}
