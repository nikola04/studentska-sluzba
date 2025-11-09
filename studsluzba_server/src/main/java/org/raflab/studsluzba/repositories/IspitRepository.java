package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Ispit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IspitRepository extends JpaRepository<Ispit, Long> {

    @Query("select i from Ispit i where i.ispitniRok.id = :ispitniRokId")
    List<Ispit> findByIspitniRokId (Long ispitniRokId);

    @Query("select avg(p.ispitIzlazak.brojPoena) from Ispit i join i.prijaveIspita p where i.id = :ispitId")
    Double findAverageOcegaByIspitniRokId(Long ispitId);

    @Query("select i from Ispit i where i.ispitniRok.id = :ispitniRokId and i.predmet.id = :predmetId")
    Ispit findByIspitniRokIdAndPredmetId(Long ispitniRokId, Long predmetId);

    @Query("select distinct i from Ispit i join i.prijaveIspita pi where pi.ispitIzlazak is not null and pi.ispitIzlazak.polozenPredmet is not null and pi.studentIndeks.id = :studentId")
    Page<Ispit> findPolozeniByStudentId(Long studentId, Pageable pageable);

    @Query("select distinct i from Ispit i join i.prijaveIspita pi where pi.studentIndeks.id = :studentId and (pi.ispitIzlazak is null or pi.ispitIzlazak.polozenPredmet is null)")
    Page<Ispit> findNepolozeniByStudentId(Long studentId, Pageable pageable);
}
