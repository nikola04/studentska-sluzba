package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PredispitnaObavezaVrsta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "predispitna-obaveza-vrsta")
public interface PredispitneObavezeVrstaRepository extends JpaRepository<PredispitnaObavezaVrsta, Long> {
}
