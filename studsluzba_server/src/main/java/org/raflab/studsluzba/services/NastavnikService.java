package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.request.NastavnikObrazovanjeRequest;
import org.raflab.studsluzba.exceptions.ResourceNotFoundException;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.raflab.studsluzba.repositories.NaucnaOblastRepository;
import org.raflab.studsluzba.repositories.UzaNaucnaOblastRepository;
import org.raflab.studsluzba.repositories.ZvanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NastavnikService {
    @Autowired
    private NastavnikRepository nastavnikRepository;
    @Autowired
    private ZvanjeRepository zvanjeRepository;
    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;
    @Autowired
    private UzaNaucnaOblastRepository uzaNaucnaOblastRepository;

    @Autowired
    private VisokoskolskaUstanovaService visokoskolskaUstanovaService;
    @Autowired
    private VrstaStudijaService vrstaStudijaService;

    private Set<NastavnikObrazovanje> fetchObrazovanja(Nastavnik nastavnik, Set<NastavnikObrazovanjeRequest> requests){
        if(requests == null) return new HashSet<>();
        Set<NastavnikObrazovanje> obrazovanja = new HashSet<>();

        requests.forEach(request -> {
            VisokoskolskaUstanova visokoskolskaUstanova = visokoskolskaUstanovaService.getVisokoskolskaUstanova(request.getVisokoskolskaUstanovaId());
            VrstaStudija vrstaStudija = vrstaStudijaService.getVrstaStudija(request.getVrstaStudijaId());

            NastavnikObrazovanje obrazovanje = new NastavnikObrazovanje();
            obrazovanje.setVisokoskolskaUstanova(visokoskolskaUstanova);
            obrazovanje.setVrstaStudija(vrstaStudija);
            obrazovanje.setNastavnik(nastavnik);

            obrazovanja.add(obrazovanje);
        });
        return obrazovanja;
    }

    private Set<NastavnikZvanje> fetchZvanja(Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja){
        for(NastavnikZvanje nastavnikZvanje : nastavnikZvanja) {
            Zvanje zvanje = zvanjeRepository.findById(nastavnikZvanje.getZvanje().getId()).orElseThrow(() -> new ResourceNotFoundException("[Zvanje] Not found: " + nastavnikZvanje.getZvanje().getId()));
            NaucnaOblast naucnaOblast = naucnaOblastRepository.findById(nastavnikZvanje.getNaucnaOblast().getId()).orElseThrow(() -> new ResourceNotFoundException("[NaucnaOblast] Not found: " + nastavnikZvanje.getNaucnaOblast().getId()));
            UzaNaucnaOblast uzaNaucnaOblast = uzaNaucnaOblastRepository.findById(nastavnikZvanje.getUzaNaucnaOblast().getId()).orElseThrow(() -> new ResourceNotFoundException("[UzaNaucnaOblast] Not found: " + nastavnikZvanje.getUzaNaucnaOblast().getId()));

            nastavnikZvanje.setZvanje(zvanje);
            nastavnikZvanje.setNaucnaOblast(naucnaOblast);
            nastavnikZvanje.setUzaNaucnaOblast(uzaNaucnaOblast);
            nastavnikZvanje.setNastavnik(nastavnik);
        }
        return nastavnikZvanja;
    }

    @Transactional
    public Nastavnik saveNastavnik(Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja, Set<NastavnikObrazovanjeRequest> obrazovanjeRequest) {
        nastavnik.setObrazovanja(this.fetchObrazovanja(nastavnik, obrazovanjeRequest));
        nastavnik.setZvanja(this.fetchZvanja(nastavnik, nastavnikZvanja));

        return nastavnikRepository.save(nastavnik);
    }

    public List<Nastavnik> getAllNastavnik() {
        return nastavnikRepository.findAll();
    }

    public Nastavnik getNastavnik(Long id) {
        return nastavnikRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("[Nastavnik] Not found: " + id));
    }

    public List<Nastavnik> findByImeAndPrezime(String name, String lastName) {
        String nameParam = (name != null && !name.isEmpty()) ? "%" + name.toLowerCase() + "%" : null;
        String lastNameParam = (lastName != null && !lastName.isEmpty()) ? "%" + lastName.toLowerCase() + "%" : null;

        return nastavnikRepository.findByImeAndPrezime(nameParam, lastNameParam);
    }

    @Transactional
    public void deleteNastavnik(Long id){
        Nastavnik existing = this.getNastavnik(id);
        nastavnikRepository.delete(existing);
    }

    @Transactional
    public Nastavnik updateNastavnik(Long id, Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja, Set<NastavnikObrazovanjeRequest> obrazovanjeRequest){
        Nastavnik existing = this.getNastavnik(id);

        if (existing.getObrazovanja() == null) existing.setObrazovanja(new HashSet<>());

        Set<NastavnikObrazovanje> newObrazovanja = this.fetchObrazovanja(existing, obrazovanjeRequest);
        Set<NastavnikZvanje> newZvanja = this.fetchZvanja(existing, nastavnikZvanja);

        existing.getObrazovanja().removeIf(obrazovanje -> !newObrazovanja.contains(obrazovanje));
        existing.getZvanja().removeIf(zvanje -> !newZvanja.contains(zvanje));

        existing.getObrazovanja().addAll(newObrazovanja);
        existing.getZvanja().addAll(newZvanja);

        existing.setIme(nastavnik.getIme());
        existing.setPrezime(nastavnik.getPrezime());
        existing.setSrednjeIme(nastavnik.getSrednjeIme());
        existing.setEmail(nastavnik.getEmail());
        existing.setBrojTelefona(nastavnik.getBrojTelefona());
        existing.setAdresa(nastavnik.getAdresa());
        existing.setDatumRodjenja(nastavnik.getDatumRodjenja());
        existing.setPol(nastavnik.getPol());
        existing.setJmbg(nastavnik.getJmbg());

        return nastavnikRepository.save(existing);
    }
}
