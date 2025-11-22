package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.NaucnaOblast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "naucna-oblast")
public interface NaucnaOblastRepository extends JpaRepository<NaucnaOblast, Long> {
}
