package org.resala.core.event.services;

import org.resala.core.domain.EventEntity;
import org.resala.core.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    EventService(final EventRepository xEventRepository) {
        eventRepository = xEventRepository;
    }

    public void saveData(final EventEntity eventEntity) {
        eventRepository.save(eventEntity);
    }

    public List<EventEntity> getAllEvents() {
        return eventRepository.findAllByOrderByEventDateDesc();
    }

    public List<EventEntity> getAllEventsByDate(String date) {
        return eventRepository.findByEventDateGreaterThanEqualOrderByEventDateDesc(date);
    }

    public EventEntity getEventById(final Long id) {
        return eventRepository.findById(id).get();
    }
}
