package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.SkolskaGodina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SkolskaGodinaRepository extends JpaRepository<SkolskaGodina, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE SkolskaGodina SET aktivan = false WHERE aktivan = true")
    void deactivateSkolskaGodina();
}
