package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.model.DrziPredmet;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SlusaPredmet;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.repositories.DrziPredmetRepository;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Operacije za raspodelu nastave - koji profesor drzi koji predmet 
 * i koji student slusa koji predmet 
 * 
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/professor/raspodelanastave")
public class RaspodelaNastaveController {
	
	@Autowired
	DrziPredmetRepository drziPredmetRepo;
	
	@Autowired
	SlusaPredmetRepository slusaPredmetRepo;
	
	@GetMapping(path = "/drzipredmet/aktivna/nastavnik/{idNastavnika}")
	public List<Predmet> getDrziPredmetUAktivnojSkolskojGodini(@PathVariable Long idNastavnika) {
		return drziPredmetRepo.getPredmetiZaNastavnikaUAktivnojSkolskojGodini(idNastavnika);			
	}
	
	
	@GetMapping(path = "/slusapredmetaktivna/{idPredmeta}/{idNastavnika}")
	public List<StudentIndeks> getSlusaPredmetUAktivnojSkolskojGodini(@PathVariable Long idPredmeta, @PathVariable Long idNastavnika) {
		return slusaPredmetRepo.getStudentiSlusaPredmetAktivnaGodina(idPredmeta, idNastavnika);			
	}
	
	@GetMapping(path = "/slusapredmetaktivna/{idDrziPredmet}")
	public List<StudentIndeks> getSlusaPredmetUAktivnojSkolskojGodiniForDrziPredmet(@PathVariable Long idDrziPredmet) {
		return slusaPredmetRepo.getStudentiSlusaPredmetZaDrziPredmet(idDrziPredmet);			
	}
	
	@GetMapping(path = "/neslusapredmetaktivna/{idDrziPredmet}")
	public List<StudentIndeks> getNeSlusaPredmetUAktivnojSkolskojGodiniForDrziPredmet(@PathVariable Long idDrziPredmet) {
		return slusaPredmetRepo.getStudentiNeSlusajuDrziPredmet(idDrziPredmet);	
	}
	
	/*
	 * TODO za sledece dve operacije napraviti batch varijatu - snimanje vise elemenata u jednoj operaciji
	 */
	
	@PostMapping(path="/drzipredmet/add") 
   	public Long addDrziPredmet (@RequestBody DrziPredmet drziPredmet) {  	    
   	    return drziPredmetRepo.save(drziPredmet).getId();
   	}
	
	@PostMapping(path="/slusapredmet/add") 
   	public Long addSlusaPredmet (@RequestBody SlusaPredmet slusaPredmet) {  	    
   	    return slusaPredmetRepo.save(slusaPredmet).getId();
   	}
	
	@DeleteMapping(path="/drzipredmet/{id}")
	public void deleteDrziPredmet(@PathVariable Long id) {
		try {
			drziPredmetRepo.deleteById(id);			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	

}
