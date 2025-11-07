package org.raflab.studsluzba.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.raflab.studsluzba.controllers.request.NastavnikObrazovanjeRequest;
import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.mappers.NastavnikMapper;
import org.raflab.studsluzba.mappers.NastavnikZvanjeMapper;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.raflab.studsluzba.services.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/nastavnik")
public class NastavnikController {
    @Autowired
    NastavnikMapper nastavnikMapper;
    @Autowired
    NastavnikZvanjeMapper nastavnikZvanjeMapper;
	@Autowired
	NastavnikService nastavnikService;
	
	@PostMapping(path = "/")
	public Long addNewNastavnik(@Valid @RequestBody NastavnikRequest nastavnikRequest) {
        Set<NastavnikZvanje> zvanja = nastavnikZvanjeMapper.toEntitySet(nastavnikRequest.getZvanja());
        Nastavnik nastavnik = nastavnikService.saveNastavnik(nastavnikMapper.toEntity(nastavnikRequest), zvanja, nastavnikRequest.getObrazovanje());

		return nastavnik.getId();
	}
	
	@GetMapping(path = "/")
	public List<NastavnikResponse> getAllNastavnik() {
		return nastavnikMapper.toResponseList(nastavnikService.getAllNastavnik());
	}

	@GetMapping(path = "/{id}")
	public NastavnikResponse getNastavnikById(@PathVariable Long id) {
		Nastavnik nastavnik = nastavnikService.getNastavnik(id);
		return nastavnikMapper.toResponse(nastavnik);
	}

    @DeleteMapping(path = "/{id}")
    public boolean deleteNastavnik(@PathVariable Long id) {
        nastavnikService.deleteNastavnik(id);
        return true;
    }

    @PatchMapping(path = "/{id}")
    public NastavnikResponse updateNastavnik(@PathVariable Long id, @Valid @RequestBody NastavnikRequest request){
        Nastavnik nastavnik = nastavnikMapper.toEntity(request);
        Set<NastavnikZvanje> zvanja = nastavnikZvanjeMapper.toEntitySet(request.getZvanja());
        Nastavnik updated = nastavnikService.updateNastavnik(id, nastavnik, zvanja, request.getObrazovanje());
        return nastavnikMapper.toResponse(updated);
    }
	
	@GetMapping(path = "/search")
	public List<NastavnikResponse> search(@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime){
        return nastavnikMapper.toResponseList(nastavnikService.findByImeAndPrezime(ime, prezime));
	}
	
}
