package org.raflab.studsluzba.controllers;

import java.util.List;
import java.util.Optional;

import org.raflab.studsluzba.controllers.request.NastavnikRequest;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.services.NastavnikService;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/nastavnik")
public class NastavnikController {

	@Autowired
	NastavnikService nastavnikService;
	
	@PostMapping(path = "/add")
	public Long addNewNastavnik(@RequestBody @Valid NastavnikRequest nastavnikRequest) {
		Nastavnik nastavnik = nastavnikService.save(Converters.toNastavnik(nastavnikRequest));
		return nastavnik.getId();
	}
	
	@GetMapping(path = "/all")
	public List<NastavnikResponse> getAllNastavnik() {
		return Converters.toNastavnikResponseList(nastavnikService.findAll());
	}

	@GetMapping(path = "/{id}")
	public NastavnikResponse getNastavnikById(@PathVariable Long id) {
		Optional<Nastavnik> rez = nastavnikService.findById(id);

		return rez.map(Converters::toNastavnikResponse).orElse(null);
	}
	
	@GetMapping(path = "path/{id}")
	public NastavnikResponse getNastavnikPath(@PathVariable Long id) {
		//TODO
		return null;
    }
	
	@GetMapping(path = "/search")
	public List<NastavnikResponse> search(
			@RequestParam(required = false) String ime,
			@RequestParam(required = false) String prezime){

        return Converters.toNastavnikResponseList(nastavnikService.findByImeAndPrezime(ime, prezime));
	}
	
}
