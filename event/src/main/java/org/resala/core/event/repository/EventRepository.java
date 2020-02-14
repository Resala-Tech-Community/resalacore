package org.resala.core.event.repository;

import org.resala.core.event.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>{

    EventEntity findEventById(Long id);
    List<EventEntity> findAllByOrderByEventDateDesc();
    List<EventEntity> findByEventDateGreaterThanEqualOrderByEventDateDesc(String eventDate);
}

