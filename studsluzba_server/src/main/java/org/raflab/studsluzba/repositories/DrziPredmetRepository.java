package org.raflab.studsluzba.repositories;

import java.util.List;


import org.raflab.studsluzba.model.DrziPredmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DrziPredmetRepository extends JpaRepository<DrziPredmet, Long> {
    @Query("select dp from DrziPredmet dp where dp.nastavnik.id = :nastavnikId and dp.predmet.id = :predmetId and dp.skolskaGodina.id = :skolskaGodinaId")
    DrziPredmet getDrziPredmetByPredmetIdNastavnikIdSkolskaGodinaId(Long predmetId, Long nastavnikId, Long skolskaGodinaId);

	@Query("select dp from DrziPredmet dp where dp.nastavnik.id = :nastavnikId ")
    List<DrziPredmet> getDrziPredmetByNastavnikId(Long nastavnikId);

    @Query("select dp from DrziPredmet dp where dp.predmet.id = :predmetId")
    List<DrziPredmet> getDrziPredmetByPredmetId(Long predmetId);

    @Query("select dp from DrziPredmet dp where dp.predmet.id = :predmetId and dp.skolskaGodina.id = :godinaId")
    List<DrziPredmet> findByPredmetIdGodinaId(Long predmetId, Long godinaId);
}
