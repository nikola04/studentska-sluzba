package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.request.NastavnikObrazovanjeRequest;
import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
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

    private void fetchZvanja(Nastavnik nastavnik, Set<NastavnikZvanjeRequest> requests){
        if(requests == null) {
            nastavnik.setZvanja(new HashSet<>());
            return;
        }

        for(NastavnikZvanjeRequest request : requests) {
            Zvanje zvanje = zvanjeRepository.findById(request.getZvanjeId()).orElseThrow(() -> new ResourceNotFoundException("[Zvanje] Not found: " + request.getZvanjeId()));
            NaucnaOblast naucnaOblast = naucnaOblastRepository.findById(request.getNaucnaOblastId()).orElseThrow(() -> new ResourceNotFoundException("[NaucnaOblast] Not found: " + request.getNaucnaOblastId()));
            UzaNaucnaOblast uzaNaucnaOblast = uzaNaucnaOblastRepository.findById(request.getUzaNaucnaOblastId()).orElseThrow(() -> new ResourceNotFoundException("[UzaNaucnaOblast] Not found: " + request.getUzaNaucnaOblastId()));

            // find Zvanje & Set properties
            NastavnikZvanje nastavnikZvanje = nastavnik.getZvanja().stream().filter(z -> z.getDatumIzbora().equals(request.getDatumIzbora())).findFirst().orElseThrow(() -> new ResourceNotFoundException("Nema zvanja sa datumom: " + request.getDatumIzbora()));

            nastavnikZvanje.setZvanje(zvanje);
            nastavnikZvanje.setNaucnaOblast(naucnaOblast);
            nastavnikZvanje.setUzaNaucnaOblast(uzaNaucnaOblast);
            nastavnikZvanje.setNastavnik(nastavnik);
        }
    }

    @Transactional
    public Nastavnik saveNastavnik(Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja, Set<NastavnikObrazovanjeRequest> obrazovanjeRequest, Set<NastavnikZvanjeRequest> zvanjaRequest) {
        if (nastavnikZvanja != null && !nastavnikZvanja.isEmpty()) {
            nastavnikZvanja.forEach(zvanje -> zvanje.setNastavnik(nastavnik));
            nastavnik.setZvanja(nastavnikZvanja);
        }

        nastavnik.setObrazovanja(this.fetchObrazovanja(nastavnik, obrazovanjeRequest));
        this.fetchZvanja(nastavnik, zvanjaRequest);

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
    public Nastavnik updateNastavnik(Long id, Nastavnik nastavnik, Set<NastavnikZvanje> nastavnikZvanja, Set<NastavnikObrazovanjeRequest> obrazovanjeRequest, Set<NastavnikZvanjeRequest> zvanjaRequest){
        Nastavnik existing = this.getNastavnik(id);

        if(obrazovanjeRequest != null) {
            Set<NastavnikObrazovanje> obrazovanja = this.fetchObrazovanja(existing, obrazovanjeRequest);
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

            this.fetchZvanja(existing, zvanjaRequest);
        }
        return nastavnikRepository.save(existing);
    }
}
