package org.raflab.studsluzba.services;

import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.model.VisokoskolskaUstanova;
import org.raflab.studsluzba.repositories.StudentPodaciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentPodaciRepository studentPodaciRepo;

    @Autowired
    private SrednjaSkolaService srednjaSkolaService;
    @Autowired
    private VisokoskolskaUstanovaService visokoskolskaUstanovaService;

    private void fetchBeforeSave(StudentPodaci studentPodaci, Long srednjaSkolaId, Long visokoskolskaUstanovaId){
        SrednjaSkola srednjaSkola = srednjaSkolaService.getSrednjaSkola(srednjaSkolaId);
        studentPodaci.setSrednjaSkola(srednjaSkola);

        if(visokoskolskaUstanovaId == null) {
            studentPodaci.setVisokoskolskaUstanova(null);
            return;
        }
        VisokoskolskaUstanova visokoskolskaUstanova = visokoskolskaUstanovaService.getVisokoskolskaUstanova(visokoskolskaUstanovaId);
        studentPodaci.setVisokoskolskaUstanova(visokoskolskaUstanova);
    }

    @Transactional
    public StudentPodaci saveStudentPodaci(StudentPodaci StudentPodaci, Long srednjaSkolaId, Long visokoskolskaUstanovaId) {
        this.fetchBeforeSave(StudentPodaci, srednjaSkolaId, visokoskolskaUstanovaId);
        return studentPodaciRepo.save(StudentPodaci);
    }

    public List<StudentPodaci> getAllStudentPodaci() {
        return studentPodaciRepo.findAll();
    }

    public List<StudentPodaci> getStudentPodaciBySrednjaSkola(Long srednjaSkolaId){
        return studentPodaciRepo.findBySrednjaSkola(srednjaSkolaId);
    }

    public StudentPodaci getStudentPodaci(Long id) {
        return studentPodaciRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("[StudentPodaci] Not found: " + id));
    }

    @Transactional
    public void deleteStudentPodaci(Long id){
        StudentPodaci existing = this.getStudentPodaci(id);
        studentPodaciRepo.delete(existing);
    }

    @Transactional
    public StudentPodaci updateStudentPodaci(Long id, StudentPodaci studentPodaci, Long srednjaSkolaId, Long visokoskolskaUstanovaId) {
        StudentPodaci existing = this.getStudentPodaci(id);
        this.fetchBeforeSave(existing, srednjaSkolaId, visokoskolskaUstanovaId);

        existing.setIme(studentPodaci.getIme());
        existing.setPrezime(studentPodaci.getPrezime());
        existing.setSrednjeIme(studentPodaci.getSrednjeIme());

        existing.setJmbg(studentPodaci.getJmbg());
        existing.setDatumRodjenja(studentPodaci.getDatumRodjenja());
        existing.setPol(studentPodaci.getPol());

        existing.setMestoRodjenja(studentPodaci.getMestoRodjenja());
        existing.setMestoPrebivalista(studentPodaci.getMestoPrebivalista());
        existing.setAdresa(studentPodaci.getAdresa());
        existing.setMestoStanovanja(studentPodaci.getMestoStanovanja());
        existing.setAdresaStanovanja(studentPodaci.getAdresaStanovanja());

        existing.setDrzavaRodjenja(studentPodaci.getDrzavaRodjenja());
        existing.setDrzavljanstvo(studentPodaci.getDrzavljanstvo());
        existing.setNacionalnost(studentPodaci.getNacionalnost());

        existing.setFakultetEmail(studentPodaci.getFakultetEmail());
        existing.setPrivatniEmail(studentPodaci.getPrivatniEmail());
        existing.setBrojTelefonaMobilni(studentPodaci.getBrojTelefonaMobilni());
        existing.setBrojTelefonaFiksni(studentPodaci.getBrojTelefonaFiksni());

        existing.setBrojLicneKarte(studentPodaci.getBrojLicneKarte());
        existing.setLicnuKartuIzdao(studentPodaci.getLicnuKartuIzdao());

        return studentPodaciRepo.save(existing);
    }
}
