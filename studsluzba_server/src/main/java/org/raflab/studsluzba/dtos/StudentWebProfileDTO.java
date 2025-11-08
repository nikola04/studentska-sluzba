package org.raflab.studsluzba.dtos;

import java.util.List;

import lombok.Data;
import org.raflab.studsluzba.model.PredmetSlusa;
import org.raflab.studsluzba.model.StudentIndeks;

@Data
public class StudentWebProfileDTO {
	
	private StudentIndeks aktivanIndeks;	

	// za aktivnu skolsku godinu
	private List<PredmetSlusa> predmeteSlusa;
	
}
