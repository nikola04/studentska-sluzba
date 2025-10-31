package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.Nastavnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Long> {	//	nasljedjene implementacije poput findById i findByAll
	
	@Query("select sp from Nastavnik sp where "
			+ "(:ime is null or lower(sp.ime) like :ime) and "
			+ "(:prezime is null or lower(sp.prezime) like :prezime)")			
	List<Nastavnik> findByImeAndPrezime(String ime, String prezime);

	List<Nastavnik> findByEmailIn(List<String> emails);
	
}

