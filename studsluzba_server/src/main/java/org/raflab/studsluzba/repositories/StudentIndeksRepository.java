package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIndeksRepository extends JpaRepository<StudentIndeks, Long> {

    @Query("select indeks from StudentIndeks indeks where indeks.student.id = :studentId and indeks.id = :indeksId")
    StudentIndeks findStudentIndeks(Long studentId, Long indeksId);

	@Query("select si from StudentIndeks si where si.student.id = :idStudentPodaci")
	List<StudentIndeks> findStudentIndeksiForStudentPodaciId(Long idStudentPodaci);

	@Query("SELECT s.broj FROM StudentIndeks s WHERE s.godina = :godina AND s.studijskiProgram.id = :studProgramId AND s.aktivan = true ORDER BY s.broj ASC")
	List<Integer> findBrojeviByGodinaAndStudProgramOznaka(@Param("godina") int godina, @Param("studProgramId") Long studProgramId);


}
