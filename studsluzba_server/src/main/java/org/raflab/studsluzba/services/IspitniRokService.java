package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.repositories.IspitniRokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class IspitniRokService {
    @Autowired
    private IspitniRokRepository ispitniRokRepository;

    public IspitniRok saveIspitniRok(IspitniRok ispitniRok) {
        return ispitniRokRepository.save(ispitniRok);
    }

    public List<IspitniRok> getAllIspitniRok() {
        return ispitniRokRepository.findAll();
    }

    public IspitniRok getIspitniRok(Long id) {
        return ispitniRokRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[IspitniRok] Not found: " + id));
    }

    @Transactional
    public void deleteIspitniRok(Long id){
        IspitniRok existing = ispitniRokRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[IspitniRok] Not found: " + id));
        ispitniRokRepository.delete(existing);
    }

    @Transactional
    public IspitniRok updateIspitniRok(Long id, IspitniRok ispitniRok){
        IspitniRok existing = ispitniRokRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[IspitniRok] Not found: " + id));

        existing.setPocetak(ispitniRok.getPocetak());
        existing.setKraj(ispitniRok.getKraj());

        return ispitniRokRepository.save(existing);
    }
}
