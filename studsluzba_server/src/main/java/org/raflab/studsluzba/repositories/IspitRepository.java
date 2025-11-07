package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Ispit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IspitRepository extends JpaRepository<Ispit, Long> {
}
