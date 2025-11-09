package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PredispitnaObaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredispitnaObavezaRepository extends JpaRepository<PredispitnaObaveza, Long> {
    @Query("SELECT p FROM PredispitnaObaveza p WHERE p.predmet.id = :predmetId")
    List<PredispitnaObaveza> findByPredmetId(Long predmetId);

    @Query("SELECT SUM(spo.brojPoena) FROM StudentPredispitnaObaveza spo WHERE spo.studentIndeks.id = :studentIndeksId and spo.predispitnaObaveza.predmet.id = :predmetId and spo.predispitnaObaveza.skolskaGodina.id = :skolskaGodinaId")
    Double sumPoeniForStudentIndeksIdByPredmetId(Long studentIndeksId, Long predmetId, Long skolskaGodinaId);
}
