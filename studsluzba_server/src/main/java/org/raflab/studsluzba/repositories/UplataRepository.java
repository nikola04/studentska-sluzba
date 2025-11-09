package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Uplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UplataRepository extends JpaRepository<Uplata, Long> {
    @Query("select u from Uplata u where u.studentIndeks.id = :id")
    List<Uplata> findByStudentIndeksId(Long id);
}
