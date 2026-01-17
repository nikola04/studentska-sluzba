package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.IspitniRok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IspitniRokRepository extends JpaRepository<IspitniRok, Long> {
    @Query("select rok from IspitniRok rok order by rok.skolskaGodina.godina desc, rok.pocetak desc")
    List<IspitniRok> findAllSorted();
}
