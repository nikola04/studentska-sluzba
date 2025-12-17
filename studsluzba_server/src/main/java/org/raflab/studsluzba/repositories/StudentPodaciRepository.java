package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.StudentPodaci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPodaciRepository extends JpaRepository<StudentPodaci, Long> {
	@Query("select sp from StudentPodaci sp where "
			+ "(:ime is null or lower(sp.ime) like :ime) and "
			+ "(:prezime is null or lower(sp.prezime) like :prezime) and"
			+ "(:srednjaSkola is null or lower(sp.srednjaSkola.naziv) like :srednjaSkola)")
	Page<StudentPodaci> findStudent(String ime, String prezime, String srednjaSkola, Pageable pageable);

    @Query("select sp from StudentPodaci sp where sp.srednjaSkola.id = :srednjaSkola")
    List<StudentPodaci> findBySrednjaSkola(@Param("srednjaSkola") Long srednjaSkolaId);
	
}
