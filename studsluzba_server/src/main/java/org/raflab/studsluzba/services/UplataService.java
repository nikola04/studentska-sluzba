package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.Iznos;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.Uplata;
import org.raflab.studsluzba.repositories.UplataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class UplataService {
    @Autowired
    private UplataRepository uplataRepository;

    @Autowired
    private StudentIndeksService studentIndeksService;
    @Autowired
    private ExchangeService exchangeService;

    public Uplata getUplata(Long studentId, Long id){
        return uplataRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Uplata] Not found: " + id));
    }

    public Uplata saveUplata(Uplata uplata, Long studentId){
        StudentIndeks studentIndeks = studentIndeksService.getStudentIndeks(studentId);
        Double kurs = exchangeService.getEuroRate();

        uplata.setDatumUplate(LocalDate.now());
        uplata.setStudentIndeks(studentIndeks);
        uplata.setKurs(kurs);

        return uplataRepository.save(uplata);
    }

    public List<Uplata> getAllUplata(Long studentId){
        return uplataRepository.findByStudentIndeksId(studentId);
    }

    public Iznos getMissingAmount(Long studentId){
        Double kurs = exchangeService.getEuroRate();
        double scholarshipEur = 3000;

        double sumEur = 0;
        double sumRsd = 0;
        for(Uplata uplata : this.getAllUplata(studentId)){
            sumRsd += uplata.getIznosDinari();
            sumEur += uplata.getIznosDinari() / uplata.getKurs();
        }

        Iznos missing = new Iznos();
        missing.setEur(scholarshipEur - sumEur);
        missing.setRsd(scholarshipEur * kurs - sumRsd);

        return missing;
    }

    @Transactional
    public void deleteUplata(Long studentId, Long id){
        Uplata existing = this.getUplata(studentId, id);
        uplataRepository.delete(existing);
    }
}
