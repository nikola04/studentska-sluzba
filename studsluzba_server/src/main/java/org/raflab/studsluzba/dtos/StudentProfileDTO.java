package org.raflab.studsluzba.dtos;

import java.util.List;

import lombok.Data;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.PredmetSlusa;
import org.raflab.studsluzba.model.StudentIndeks;

/*
 * objekat ove kalse sadrzi sve podatke o studentu koji
 * se prikazuju u njegovom profilu  
 * 
 * - polozeni predmeti
 * - tok studija (upisi, obnove godina)
 * - predmete koje slusa
 * - prijavljeni ispiti
 * - uplate
 * 
 * - selektujemo preko indeksa, potrebno prikupiti podatke i o drugim indeksima
 * 
 */

@Data
public class StudentProfileDTO {
	
	private StudentIndeks indeks;	

	
	// za aktivnu skolsku godinu
	
	private List<PredmetSlusa> predmeteSlusa;
	
	private List<Predmet> nepolozeniPredmeti;
	
}
