package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.IspitniRok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IspitniRokRepository extends JpaRepository<IspitniRok, Long> {
}
