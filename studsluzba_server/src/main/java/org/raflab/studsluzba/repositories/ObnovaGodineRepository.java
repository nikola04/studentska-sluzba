package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.ObnovaGodine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObnovaGodineRepository extends JpaRepository<ObnovaGodine, Long> {
    @Query("select o from ObnovaGodine o where o.studentIndeks.id = :studentId and o.skolskaGodina.id = :godinaId")
    ObnovaGodine findObnovaGodineByStudentIndeksIdGodinaId(Long studentId, Long godinaId);

    @Query("select o from ObnovaGodine o where o.studentIndeks.id = :studentId")
    List<ObnovaGodine> findAllByStudentIndeksId(Long studentId);
}
