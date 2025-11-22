package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Zvanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "zvanje")
public interface ZvanjeRepository extends JpaRepository<Zvanje, Long> {
}
