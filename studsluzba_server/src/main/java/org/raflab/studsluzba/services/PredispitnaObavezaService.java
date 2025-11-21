package org.raflab.studsluzba.services;


import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.PredispitnaObaveza;
import org.raflab.studsluzba.model.PredispitnaObavezaVrsta;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.PredispitnaObavezaRepository;
import org.raflab.studsluzba.repositories.PredispitneObavezeVrstaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PredispitnaObavezaService {
    @Autowired
    private PredispitnaObavezaRepository predispitnaObavezaRepository;
    @Autowired
    private PredispitneObavezeVrstaRepository predispitneObavezeVrstaRepository;

    @Autowired
    private PredmetService predmetService;
    @Autowired
    private SkolskaGodinaService skolskaGodinaService;

    private void fetchChilds(PredispitnaObaveza predispitnaObaveza, Long predmetId, Long skolskaGodinaId, Long predispitnaObavezaVrstaId){
        Predmet predmet = predmetService.getPredmet(predmetId);
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);
        PredispitnaObavezaVrsta predispitnaObavezaVrsta = predispitneObavezeVrstaRepository.findById(predispitnaObavezaVrstaId).orElseThrow(() -> new ResourceNotFoundException("[PredispitnaObavezaVrsta] Not found: " + predispitnaObavezaVrstaId));

        predispitnaObaveza.setPredmet(predmet);
        predispitnaObaveza.setSkolskaGodina(skolskaGodina);
        predispitnaObaveza.setVrsta(predispitnaObavezaVrsta);
    }

    public PredispitnaObaveza getPredispitnaObaveza(Long id){
        return predispitnaObavezaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[PredispitnaObaveza] Not found: " + id));
    }

    public List<PredispitnaObaveza> getAllPredispitnaObavezaForPredmetId(Long predmetId){
        return predispitnaObavezaRepository.findByPredmetId(predmetId);
    }

    public Double getPoeniForStudentIndeksByPredmetId(Long studentIndeksId, Long predmetId, Long skolskaGodinaId){
        return predispitnaObavezaRepository.sumPoeniForStudentIndeksIdByPredmetId(studentIndeksId, predmetId, skolskaGodinaId);
    }

    @Transactional
    public PredispitnaObaveza savePredispitnaObaveza(PredispitnaObaveza predispitnaObaveza, Long predmetId, Long skolskaGodinaId, Long predispitnaObavezaVrstaId){
        fetchChilds(predispitnaObaveza, predmetId, skolskaGodinaId, predispitnaObavezaVrstaId);
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
    public PredispitnaObaveza updatePredispitnaObaveza(Long id, Long predmetId, Long skolskaGodinaId, Long predispitnaObavezaVrstaId, PredispitnaObaveza predispitnaObaveza){
        PredispitnaObaveza existing = this.getPredispitnaObaveza(id);

        existing.setVrsta(predispitnaObaveza.getVrsta());
        existing.setMaxBrojPoena(predispitnaObaveza.getMaxBrojPoena());

        fetchChilds(existing, predmetId, skolskaGodinaId, predispitnaObavezaVrstaId);
        return predispitnaObavezaRepository.save(existing);
    }
}
