package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.IspitIzlazak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IspitIzlazakRepository extends JpaRepository<IspitIzlazak, Long> {
    @Query("select i from IspitIzlazak i join i.ispitPrijava.studentIndeks indeks where i.ispitPrijava.ispit.id = :ispitId ORDER BY indeks.studijskiProgram.naziv, indeks.godina, indeks.broj")
    List<IspitIzlazak> findAllByIspitIdSorted(Long ispitId);

    @Query("select count(i) from IspitIzlazak i where i.ispitPrijava.studentIndeks.id = :studentId and i.ispitPrijava.ispit.predmet.id = :predmetId")
    Integer countIzlazakByStudentIdPredmetId(Long studentId, Long predmetId);
}
