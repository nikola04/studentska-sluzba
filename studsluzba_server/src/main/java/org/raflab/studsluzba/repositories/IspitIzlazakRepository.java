package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.IspitIzlazak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IspitIzlazakRepository extends JpaRepository<IspitIzlazak, Long> {
}
