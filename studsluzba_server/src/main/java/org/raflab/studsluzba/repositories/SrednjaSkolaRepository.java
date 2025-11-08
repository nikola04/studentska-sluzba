package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.SrednjaSkola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SrednjaSkolaRepository extends JpaRepository<SrednjaSkola, Long> {
}
