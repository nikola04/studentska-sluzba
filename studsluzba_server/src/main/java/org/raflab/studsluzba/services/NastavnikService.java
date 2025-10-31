package org.raflab.studsluzba.services;

import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NastavnikService {

    @Autowired
    NastavnikRepository nastavnikRepository;

    public Nastavnik save(Nastavnik nastavnik) {
        return nastavnikRepository.save(nastavnik);
    }

    public Iterable<Nastavnik> findAll() {
        return nastavnikRepository.findAll();
    }

    public Optional<Nastavnik> findById(Long id) {
        return nastavnikRepository.findById(id);
    }

    public List<Nastavnik> findByImeAndPrezime(String ime, String prezime) {
        return nastavnikRepository.findByImeAndPrezime(ime, prezime);
    }
}
