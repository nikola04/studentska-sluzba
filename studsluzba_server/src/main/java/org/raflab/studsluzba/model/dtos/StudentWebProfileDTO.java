package org.raflab.studsluzba.model.dtos;

import java.util.List;

import lombok.Data;
import org.raflab.studsluzba.model.SlusaPredmet;
import org.raflab.studsluzba.model.StudentIndeks;

@Data
public class StudentWebProfileDTO {
	
	private StudentIndeks aktivanIndeks;	

	// za aktivnu skolsku godinu
	private List<SlusaPredmet> slusaPredmete;
	
}
