package org.resala.core.repository;

import org.resala.core.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{

    List<EventEntity> findAll();
    EventEntity findEventById(Long id);
    //List<EventEntity> findAllOrderByEventDate();
    List<EventEntity> findByBranch(String branch);
    List<EventEntity> findByActivity(String activity);
}

