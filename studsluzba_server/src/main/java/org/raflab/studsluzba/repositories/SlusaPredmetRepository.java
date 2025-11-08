package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.PredmetSlusa;
import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlusaPredmetRepository extends JpaRepository<PredmetSlusa, Long> {

    @Query("select sp from PredmetSlusa sp where sp.drziPredmet.id = :drziPredmetId and sp.studentIndeks.id = :studentIndeksId")
    PredmetSlusa findByDrziPredmetIdAndStudentIndeksId(Long drziPredmetId, Long studentIndeksId);
	
	@Query("select sp from PredmetSlusa sp where sp.studentIndeks.id = :studentIndeksId")
	List<PredmetSlusa> findSlusaPredmetByStudentIndeksId(Long studentIndeksId);
	
	
	@Query("select sp.studentIndeks from PredmetSlusa sp where sp.drziPredmet.predmet.id = :idPredmeta "
			+ "and sp.drziPredmet.nastavnik.id = :idNastavnika  ")
	List<StudentIndeks> getStudentiSlusaPredmetAktivnaGodina(Long idPredmeta, Long idNastavnika);
	
	
	@Query("select sp.studentIndeks from PredmetSlusa sp where sp.drziPredmet.id = :idDrziPredmet")
	List<StudentIndeks> getStudentiSlusaPredmetZaDrziPredmet(Long idDrziPredmet);
	
	
	/*
	 * TODO dodati slicne operacije koja vracaju sve studente za stud program/ godinu studija koje ne slusaju predmet
	 */
	@Query("select si from StudentIndeks si where not exists "
			+ "(select sp from PredmetSlusa sp where sp.studentIndeks=si and sp.drziPredmet.id = :idDrziPredmet) ")
	List<StudentIndeks> getStudentiNeSlusajuDrziPredmet(Long idDrziPredmet);
	

}
