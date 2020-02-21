package org.resala.core.event.repository;

import org.resala.core.domain.BranchEventEntity;
import org.resala.core.domain.key.BranchEventKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchEventRepository extends JpaRepository<BranchEventEntity, BranchEventKey> {
}
