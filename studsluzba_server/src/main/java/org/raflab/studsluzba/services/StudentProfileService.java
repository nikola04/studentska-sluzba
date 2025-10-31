package org.raflab.studsluzba.services;

import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.dtos.StudentProfileDTO;
import org.raflab.studsluzba.model.dtos.StudentWebProfileDTO;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.StudentPodaciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {
	@Autowired
	StudentIndeksRepository studentIndeksRepo;
	
	@Autowired
	StudentPodaciRepository studentPodaciRepo;

	@Autowired
	SlusaPredmetRepository slusaPredmetRepo;
	
	@Autowired
	PredmetRepository predmetRepo;

	public StudentProfileDTO getStudentProfile(Long indeksId) {
		StudentProfileDTO retVal = new StudentProfileDTO();
		StudentIndeks studIndeks = studentIndeksRepo.findById(indeksId).get();		
		retVal.setIndeks(studIndeks);		
		retVal.setSlusaPredmete(slusaPredmetRepo.getSlusaPredmetForIndeksAktivnaGodina(indeksId));
		return retVal;
	}
	
	public StudentWebProfileDTO getStudentWebProfile(Long indeksId) {
		StudentWebProfileDTO retVal = new StudentWebProfileDTO();
		StudentIndeks studIndeks = studentIndeksRepo.findById(indeksId).get();
		Long studPodaciId = studIndeks.getStudent().getId();
		retVal.setAktivanIndeks(studentPodaciRepo.getAktivanIndeks(studPodaciId));		
		retVal.setSlusaPredmete(slusaPredmetRepo.getSlusaPredmetForIndeksAktivnaGodina(indeksId));
		return retVal;
	}

}
