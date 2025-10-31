package org.raflab.studsluzba.controllers;

import java.util.List;

import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path="/api/studprogram")
public class StudijskiProgramiController {
	
	@Autowired
	StudijskiProgramRepository studijskiProgramiRepo;
	
	@GetMapping(path = "/all/sorted")
	public List<StudijskiProgram> getAllStudProgramiSortedDesc(){
		return studijskiProgramiRepo.getAllSortedByGodinaDesc();
	}
}
