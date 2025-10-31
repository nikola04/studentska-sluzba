package org.raflab.studsluzba.controllers;

import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path="/api/coder")
public class SifarniciController {
	@Autowired
	StudijskiProgramRepository studProgramRepository;

	@GetMapping(path="/studprogram/oznaka/all")
    public Iterable<String> getAllStudProgramOznaka() {      
      return studProgramRepository.findAllOznaka();
    }
	
}
