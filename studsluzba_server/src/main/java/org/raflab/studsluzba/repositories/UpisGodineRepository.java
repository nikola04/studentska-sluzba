package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.UpisGodine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpisGodineRepository extends JpaRepository<UpisGodine, Long> {
    @Query("select u from UpisGodine u where u.studentIndeks.id = :id order by u.datumUpisa desc")
    UpisGodine findLatestUpisByStudentIndeksId(Long id);

    @Query("select u from UpisGodine u where u.studentIndeks.id = :id")
    List<UpisGodine> findAllByStudentIndeksId(Long id);
}
