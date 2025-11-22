package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.TipSkole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "tip-skole")
public interface TipSkoleRepository extends JpaRepository<TipSkole, Long> {
}
