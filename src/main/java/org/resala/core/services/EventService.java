package org.resala.core.services;

import jdk.jfr.Event;
import org.resala.core.entities.EventEntity;
import org.resala.core.repository.EventRepository;

import org.springframework.data.domain.Sort;
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
        return eventRepository.findAll();
    }

    public List<EventEntity> getAllEventsByBranch(String branch)
    {
        return eventRepository.findByBranch(branch);
    }

    public List<EventEntity> getAllEventsByActivity(String activity)
    {
        return eventRepository.findByActivity(activity);
    }

    public EventEntity getEventById(final Long id) {
        return eventRepository.findById(id).get();
    }
}
