package org.raflab.studsluzba.services;


import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.PredispitnaObaveza;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.PredispitnaObavezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PredispitnaObavezaService {
    @Autowired
    private PredispitnaObavezaRepository predispitnaObavezaRepository;

    @Autowired
    private PredmetService predmetService;
    @Autowired
    private SkolskaGodinaService skolskaGodinaService;

    private void fetchChilds(PredispitnaObaveza predispitnaObaveza, Long predmetId, Long skolskaGodinaId){
        Predmet predmet = predmetService.getPredmet(predmetId);
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);

        predispitnaObaveza.setPredmet(predmet);
        predispitnaObaveza.setSkolskaGodina(skolskaGodina);
    }

    public PredispitnaObaveza getPredispitnaObaveza(Long id){
        return predispitnaObavezaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[PredispitnaObaveza] Not found: " + id));
    }

    public List<PredispitnaObaveza> getAllPredispitnaObavezaForPredmetId(Long predmetId){
        return predispitnaObavezaRepository.findByPredmetId(predmetId);
    }

    @Transactional
    public PredispitnaObaveza savePredispitnaObaveza(PredispitnaObaveza predispitnaObaveza, Long predmetId, Long skolskaGodinaId){
        fetchChilds(predispitnaObaveza, predmetId, skolskaGodinaId);
        return predispitnaObavezaRepository.save(predispitnaObaveza);
    }

    @Transactional
    public void deletePredispitnaObaveza(Long predmetId, Long id){
        PredispitnaObaveza po = this.getPredispitnaObaveza(id);
        if(!predmetId.equals(po.getPredmet().getId()))
            throw new ResourceNotFoundException("[PredispitnaObaveza] Not found: " + id + ", with predmetId: " + predmetId);
        predispitnaObavezaRepository.deleteById(id);
    }

    @Transactional
    public PredispitnaObaveza updatePredispitnaObaveza(Long id, Long predmetId, Long skolskaGodinaId, PredispitnaObaveza predispitnaObaveza){
        PredispitnaObaveza existing = this.getPredispitnaObaveza(id);

        existing.setVrsta(predispitnaObaveza.getVrsta());
        existing.setMaxBrojPoena(predispitnaObaveza.getMaxBrojPoena());

        fetchChilds(existing, predmetId, skolskaGodinaId);
        return predispitnaObavezaRepository.save(existing);
    }
}
