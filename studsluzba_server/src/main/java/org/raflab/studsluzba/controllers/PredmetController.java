package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/predmet")
public class PredmetController {
	
	@Autowired
	PredmetRepository predmetRepo;
	
	
	@GetMapping(path = "/all")
	public Iterable<Predmet> getPredmetiForGodinaAkreditacije(){
		return predmetRepo.findAll();
		
	}
	
	@GetMapping(path = "/all/{godinaAkreditacije}")
	public List<Predmet> getPredmetiForGodinaAkreditacije(@PathVariable Integer godinaAkreditacije){
		return predmetRepo.getPredmetForGodinaAkreditacije(godinaAkreditacije);
		
	}
	
}
