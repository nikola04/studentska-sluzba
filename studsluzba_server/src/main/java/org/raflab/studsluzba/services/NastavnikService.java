package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NastavnikService {
    @Autowired
    NastavnikRepository nastavnikRepository;

    @Transactional
    public Nastavnik saveNastavnik(Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja) {
        if (nastavnikZvanja != null && !nastavnikZvanja.isEmpty()) {
            nastavnikZvanja.forEach(zvanje -> zvanje.setNastavnik(nastavnik));
            nastavnik.setZvanja(nastavnikZvanja);
        }

        return nastavnikRepository.save(nastavnik);
    }

    public List<Nastavnik> getAllNastavnik() {
        return nastavnikRepository.findAll();
    }

    public Nastavnik getNastavnik(Long id) {
        return nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }

    public List<Nastavnik> findByImeAndPrezime(String ime, String prezime) {
        return nastavnikRepository.findByImeAndPrezime(ime, prezime);
    }

    public void deleteNastavnik(Long id){
        Nastavnik existing = nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        nastavnikRepository.delete(existing);
    }

    @Transactional
    public Nastavnik updateNastavnik(Long id, Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja){
        Nastavnik existing = nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        existing.setIme(nastavnik.getIme());
        existing.setPrezime(nastavnik.getPrezime());
        existing.setSrednjeIme(nastavnik.getSrednjeIme());
        existing.setEmail(nastavnik.getEmail());
        existing.setBrojTelefona(nastavnik.getBrojTelefona());
        existing.setAdresa(nastavnik.getAdresa());
        existing.setDatumRodjenja(nastavnik.getDatumRodjenja());
        existing.setPol(nastavnik.getPol());
        existing.setJmbg(nastavnik.getJmbg());

        if (nastavnikZvanja != null) {
            existing.getZvanja().removeIf(zvanje -> !nastavnikZvanja.contains(zvanje));

            nastavnikZvanja.forEach(zvanje -> {
                zvanje.setNastavnik(existing);
                existing.getZvanja().add(zvanje);
            });
        }
        return nastavnikRepository.save(existing);
    }
}
