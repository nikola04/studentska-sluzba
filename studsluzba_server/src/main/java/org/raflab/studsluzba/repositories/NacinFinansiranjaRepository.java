package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.NacinFinansiranja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "nacin-finansiranja")
public interface NacinFinansiranjaRepository extends JpaRepository<NacinFinansiranja, Long> {
}
