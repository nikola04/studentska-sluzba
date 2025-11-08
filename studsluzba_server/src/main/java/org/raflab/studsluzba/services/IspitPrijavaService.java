package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceAlreadyExistsException;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.IspitPrijava;
import org.raflab.studsluzba.repositories.IspitPrijavaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IspitPrijavaService {
    @Autowired
    private IspitPrijavaRepository ispitPrijavaRepository;

    @Autowired
    private IspitService ispitService;
    @Autowired
    private StudentIndeksService studentIndeksService;

    private void fetchChilds(IspitPrijava target, Long ispitId, Long studentIndeksId){
        target.setIspit(ispitService.getIspit(ispitId));
        target.setStudentIndeks(studentIndeksService.getStudentIndeks(studentIndeksId));
    }

    public IspitPrijava getIspitPrijavaByStudentIndeksIdAndIspitId(Long indeksId, Long ispitId) {
        IspitPrijava ispitPrijava = ispitPrijavaRepository.findByIspitIdAndStudentIndeksId(indeksId, ispitId);
        if(ispitPrijava == null)
            throw new ResourceNotFoundException("[IspitPrijava] Not found: " + indeksId + " " + ispitId);

        return ispitPrijava;
    }

    public List<IspitPrijava> getAllIspitPrijavaByStudentIndeksId(Long studentIndeksId){
        return ispitPrijavaRepository.findByStudentIndeksId(studentIndeksId);
    }

    public IspitPrijava saveIspitPrijava(Long ispitId, Long indeksId){
        if(ispitPrijavaRepository.findByIspitIdAndStudentIndeksId(indeksId, ispitId) != null)
            throw new ResourceAlreadyExistsException("[IspitPrijava] You have already signed this ispit");

        IspitPrijava ispitPrijava = new IspitPrijava();

        fetchChilds(ispitPrijava, ispitId, indeksId);
        ispitPrijava.setDatumPrijave(LocalDate.now());

        return ispitPrijavaRepository.save(ispitPrijava);
    }

    public void deleteIspitPrijava(Long ispitId, Long indeksId){
        IspitPrijava existing = this.getIspitPrijavaByStudentIndeksIdAndIspitId(indeksId, ispitId);
        ispitPrijavaRepository.delete(existing);
    }
}
