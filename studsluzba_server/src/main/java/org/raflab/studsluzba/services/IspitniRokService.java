package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.IspitniRokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class IspitniRokService {
    @Autowired
    private IspitniRokRepository ispitniRokRepository;
    @Autowired
    private SkolskaGodinaService skolskaGodinaService;

    public IspitniRok saveIspitniRok(IspitniRok ispitniRok, Long skolskaGodinaId) {
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);
        ispitniRok.setSkolskaGodina(skolskaGodina);

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
    public IspitniRok updateIspitniRok(Long id, IspitniRok ispitniRok, Long skolskaGodinaId){
        IspitniRok existing = ispitniRokRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[IspitniRok] Not found: " + id));
        SkolskaGodina skolskaGodina = skolskaGodinaService.getSkolskaGodina(skolskaGodinaId);

        existing.setPocetak(ispitniRok.getPocetak());
        existing.setKraj(ispitniRok.getKraj());
        existing.setSkolskaGodina(skolskaGodina);

        return ispitniRokRepository.save(existing);
    }
}
