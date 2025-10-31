package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Grupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupaRepository extends JpaRepository<Grupa, Long> {
}
