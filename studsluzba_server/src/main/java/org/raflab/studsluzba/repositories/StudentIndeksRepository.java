package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIndeksRepository extends JpaRepository<StudentIndeks, Long> {
	@Query("select si from StudentIndeks si where si.student.id = :idStudentPodaci")
	List<StudentIndeks> findStudentIndeksiForStudentPodaciId(Long idStudentPodaci);

	@Query("SELECT s.broj FROM StudentIndeks s WHERE s.godina = :godina AND s.studijskiProgram.id = :studProgramId ORDER BY s.broj ASC")
	List<Integer> findBrojeviByGodinaAndStudProgramOznaka(@Param("godina") int godina, @Param("studProgramId") Long studProgramId);

    @Query("select si from StudentIndeks si JOIN si.prijaveIspita p WHERE p.ispit.id = :ispitId")
    List<StudentIndeks> findByIspitId(Long ispitId);

    @Query("select si from StudentIndeks si WHERE si.broj = :broj AND si.godina = :godina AND si.studijskiProgram.oznaka = :smerOznaka")
    StudentIndeks findStudentIndeksByBroj(int broj, int godina, String smerOznaka);
}
