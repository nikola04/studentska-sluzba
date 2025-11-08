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
}
