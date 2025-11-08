package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.StudentPredispitnaObaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPredispitnaObavezaRepository extends JpaRepository<StudentPredispitnaObaveza, Long> {
    @Query("select spo from StudentPredispitnaObaveza spo where spo.studentIndeks.id = :studentIndeksId")
    List<StudentPredispitnaObaveza> findAllByStudentIndeksId(Long studentIndeksId);

    @Query("select spo from StudentPredispitnaObaveza spo where spo.studentIndeks.id = :studentIndeksId and spo.predispitnaObaveza.predmet.id = :predmetId")
    List<StudentPredispitnaObaveza> findAllByStudentIndeksIdAndPredmetId(Long studentIndeksId, Long predmetId);

    @Query("select spo from StudentPredispitnaObaveza spo where spo.studentIndeks.id = :studentIndeksId and spo.predispitnaObaveza.id = :predispitnaObavezaId")
    StudentPredispitnaObaveza findByStudentIndeksIdAndPredispitnaId(Long studentIndeksId, Long predispitnaObavezaId);
}
