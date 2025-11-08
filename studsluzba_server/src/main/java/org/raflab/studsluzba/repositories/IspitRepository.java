package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Ispit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IspitRepository extends JpaRepository<Ispit, Long> {

    @Query("select i from Ispit i where i.ispitniRok.id = :ispitniRokId")
    List<Ispit> findByIspitniRokId (Long ispitniRokId);
}
