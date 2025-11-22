package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.UzaNaucnaOblast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "uza-naucna-oblast")
public interface UzaNaucnaOblastRepository extends JpaRepository<UzaNaucnaOblast, Long> {
}
