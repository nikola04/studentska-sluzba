package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.IspitPrijava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IspitPrijavaRepository extends JpaRepository<IspitPrijava, Long> {
    @Query("select ip from IspitPrijava ip where ip.studentIndeks.id = :indeksId and ip.ispit.id = :ispitId")
    IspitPrijava findByIspitIdAndStudentIndeksId(Long indeksId, Long ispitId);

    @Query("select ip from IspitPrijava ip where ip.studentIndeks.id = :indeksId")
    List<IspitPrijava> findByStudentIndeksId(Long indeksId);
}
