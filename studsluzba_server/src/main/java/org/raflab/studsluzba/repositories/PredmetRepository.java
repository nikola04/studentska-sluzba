package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
    @Query("select p from Predmet p where p.sifra = :sifra")
    Predmet findBySifra(String sifra);


    @Query("SELECT p FROM Predmet p WHERE p.studProgram.id = :id")
    List<Predmet> findPredmetForStudijskiProgram(Long id);

    @Query("SELECT COUNT(p) FROM Predmet p WHERE p.studProgram.id = :studProgramId")
    Long countByStudijskiProgramId(@Param("studProgramId") Long studProgramId);

    @Query("SELECT p FROM Predmet p join p.drzePredmetList dp join dp.slusajuPredmtList sp where sp.studentIndeks.id = :studentId and sp.drziPredmet.skolskaGodina.id = :skolskaGodinaId and p.id not in (select pp.predmet.id from PolozenPredmet pp where pp.studentIndeks.id = :studentId)")
    List<Predmet> findNepolozeniPredmeti(Long studentId, Long skolskaGodinaId);
}
