package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.VisokoskolskaUstanova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisokoskolskaUstanovaRepository extends JpaRepository<VisokoskolskaUstanova, Long> {
}
