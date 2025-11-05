package org.raflab.studsluzba.services;

import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PredmetService {
    @Autowired
    PredmetRepository predmetRepository;
    public Predmet savePredmet(Predmet predmet) {
       return this.predmetRepository.save(predmet);
    }
    public Iterable<Predmet> getAllPredmets() {
        return predmetRepository.findAll();
    }
}
