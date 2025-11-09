package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.IspitIzlazak;
import org.raflab.studsluzba.model.IspitPrijava;
import org.raflab.studsluzba.repositories.IspitIzlazakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IspitIzlazakService {
    @Autowired
    private IspitIzlazakRepository ispitIzlazakRepository;

    @Autowired
    private IspitPrijavaService ispitPrijavaService;

    public IspitIzlazak getIspitIzlazak(Long id){
        return ispitIzlazakRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("[IspitIzlazak] Not found: " + id));
    }

    public List<IspitIzlazak> getAllIspitIzlazakByIspitId(Long ispitId){
        return ispitIzlazakRepository.findAllByIspitIdSorted(ispitId);
    }

    public Integer getIzlazakCountForStudentByPredmetId(Long studentIndeksId, Long predmetId){
        return ispitIzlazakRepository.countIzlazakByStudentIdPredmetId(studentIndeksId, predmetId);
    }

    @Transactional
    public IspitIzlazak saveIspitIzlazak(IspitIzlazak ispitIzlazak, Long studentIndeksId, Long ispitId){
        IspitPrijava ispitPrijava = ispitPrijavaService.getIspitPrijavaByStudentIndeksIdAndIspitId(studentIndeksId, ispitId);

        if(ispitPrijava.getIspitIzlazak() != null)
            throw new ResourceAlreadyExistsException("[IspitIzlazak] Student has already taken exam");

        ispitIzlazak.setIspitPrijava(ispitPrijava);
        ispitPrijava.setIspitIzlazak(ispitIzlazak);

        return ispitIzlazakRepository.save(ispitIzlazak);
    }

    @Transactional
    public void deleteIspitIzlazak(Long studentIndeksId, Long ispitId){
        IspitPrijava ispitPrijava = ispitPrijavaService.getIspitPrijavaByStudentIndeksIdAndIspitId(studentIndeksId, ispitId);

        IspitIzlazak izlazak = ispitPrijava.getIspitIzlazak();
        if(izlazak == null)
            throw new ResourceNotFoundException("[IspitIzlazak] Student has not taken exam");

        ispitPrijava.setIspitIzlazak(null);
        izlazak.setIspitPrijava(null);

        ispitIzlazakRepository.delete(izlazak);
    }
}
