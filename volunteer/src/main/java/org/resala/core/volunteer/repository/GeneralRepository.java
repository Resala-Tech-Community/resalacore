package org.resala.core.volunteer.repository;

import org.resala.core.volunteer.entities.SimpleEntityParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralRepository<T extends SimpleEntityParent> extends JpaRepository<T, Long> {
}
