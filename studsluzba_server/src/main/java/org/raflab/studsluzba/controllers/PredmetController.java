package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.services.PredmetService;
import org.raflab.studsluzba.services.SkolskaGodinaService;
import org.raflab.studsluzba.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/predmet")
public class PredmetController {
	
	@Autowired
	PredmetRepository predmetRepo;
    @Autowired
    private PredmetService predmetService;
    @Autowired
    private SkolskaGodinaService skolskaGodinaService;

	@GetMapping(path = "/predmeti")
	public Iterable<Predmet> getAllPredmet() {
		Iterable<Predmet>predmeti = predmetService.getAllPredmets();
		return Converters.toPredmetResponseList(predmeti);
	}
	@GetMapping(path = "/")
	public Iterable<Predmet> getPredmetiForGodinaAkreditacije(){
		return predmetRepo.findAll();
		
	}
	
	@GetMapping(path = "/{godinaAkreditacije}")
	public List<Predmet> getPredmetiForGodinaAkreditacije(@PathVariable Integer godinaAkreditacije){
		return predmetRepo.getPredmetForGodinaAkreditacije(godinaAkreditacije);
		
	}
	@PostMapping(path = "/" )
	public Long createPredmet(@RequestBody PredmetRequest request) {
		Predmet predmet = predmetService.savePredmet(Converters.toPredmet(request));
		return predmet.getId();
	}

	
}
