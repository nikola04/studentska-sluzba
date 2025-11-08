package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PolozenPredmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolozenPredmetRepository extends JpaRepository<PolozenPredmet, Long> {
    @Query("select p from PolozenPredmet p where p.studentIndeks.id = :studentIndeksId")
    List<PolozenPredmet> findByStudentIndeksId(Long studentIndeksId);

    @Query("select p from PolozenPredmet p where p.studentIndeks.id = :studentIndeksId and p.predmet.id = :predmetId")
    PolozenPredmet findByStudentIndeksIdPredmetId(Long studentIndeksId, Long predmetId);
}
