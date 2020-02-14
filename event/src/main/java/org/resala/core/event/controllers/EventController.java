package org.resala.core.event.controllers;

import org.resala.core.event.entities.EventEntity;
import org.resala.core.event.services.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("event")
public class EventController {
    private final EventService eventService;

    public EventController(final EventService service) {
        eventService = service;
    }

    @GetMapping("all")
    public ResponseEntity getEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEventsByDate(@PathVariable("id") long id) {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @GetMapping("date")
    public ResponseEntity getEventsByDate(@RequestParam String value) {
        String currentDate = StringUtils.isEmpty(value) ? LocalDate.now().toString() : value;
        return new ResponseEntity<>(eventService.getAllEventsByDate(value), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity addEvent(@RequestBody EventEntity eventEntity) {
        eventService.saveData(eventEntity);
        return ResponseEntity.ok().build();
    }
}
