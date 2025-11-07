package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.request.NastavnikObrazovanjeRequest;
import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NastavnikService {
    @Autowired
    private NastavnikRepository nastavnikRepository;

    @Autowired
    private VisokoskolskaUstanovaService visokoskolskaUstanovaService;
    @Autowired
    private VrstaStudijaService vrstaStudijaService;

    private Set<NastavnikObrazovanje> fetchObrazovanja(Nastavnik nastavnik, Set<NastavnikObrazovanjeRequest> requests){
        if(requests == null) return null;
        Set<NastavnikObrazovanje> obrazovanja = new HashSet<>();

        requests.forEach(request -> {
            VisokoskolskaUstanova visokoskolskaUstanova = visokoskolskaUstanovaService.getVisokoskolskaUstanovaById(request.getVisokoskolskaUstanovaId());
            VrstaStudija vrstaStudija = vrstaStudijaService.findVrstaStudijaById(request.getVrstaStudijaId());

            NastavnikObrazovanje obrazovanje = new NastavnikObrazovanje();
            obrazovanje.setVisokoskolskaUstanova(visokoskolskaUstanova);
            obrazovanje.setVrstaStudija(vrstaStudija);
            obrazovanje.setNastavnik(nastavnik);

            obrazovanja.add(obrazovanje);
        });
        return obrazovanja;
    }

    @Transactional
    public Nastavnik saveNastavnik(Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja, Set<NastavnikObrazovanjeRequest> obrazovanjeRequest) {
        if (nastavnikZvanja != null && !nastavnikZvanja.isEmpty()) {
            nastavnikZvanja.forEach(zvanje -> zvanje.setNastavnik(nastavnik));
            nastavnik.setZvanja(nastavnikZvanja);
        }

        nastavnik.setObrazovanja(fetchObrazovanja(nastavnik, obrazovanjeRequest));

        return nastavnikRepository.save(nastavnik);
    }

    public List<Nastavnik> getAllNastavnik() {
        return nastavnikRepository.findAll();
    }

    public Nastavnik getNastavnik(Long id) {
        return nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Nastavnik] Not found: " + id));
    }

    public List<Nastavnik> findByImeAndPrezime(String ime, String prezime) {
        return nastavnikRepository.findByImeAndPrezime(ime, prezime);
    }

    @Transactional
    public void deleteNastavnik(Long id){
        Nastavnik existing = nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Nastavnik] Not found: " + id));
        nastavnikRepository.delete(existing);
    }

    @Transactional
    public Nastavnik updateNastavnik(Long id, Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja, Set<NastavnikObrazovanjeRequest> obrazovanjeRequest){
        Nastavnik existing = nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Nastavnik] Not found: " + id));

        if(obrazovanjeRequest != null) {
            Set<NastavnikObrazovanje> obrazovanja = fetchObrazovanja(existing, obrazovanjeRequest);
            existing.getObrazovanja().removeIf(obrazovanje -> !obrazovanja.contains(obrazovanje));
            existing.getObrazovanja().addAll(obrazovanja);
        }

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
