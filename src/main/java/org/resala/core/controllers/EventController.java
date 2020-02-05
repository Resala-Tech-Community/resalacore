package org.resala.core.controllers;

import org.resala.core.entities.EventEntity;
import org.resala.core.services.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event")
public class EventController {
    private final EventService eventService;

    public EventController(final EventService service) {
        eventService = service;
    }

    @GetMapping("all")
    public ResponseEntity getEvents() {
        return new ResponseEntity(eventService.getAllEvents(), HttpStatus.ACCEPTED);
    }


    @GetMapping("branch/{branch}")
    public ResponseEntity getEventsByBranch(@PathVariable String branch){ return  new ResponseEntity(eventService.getAllEventsByBranch(branch),HttpStatus.ACCEPTED);}

    @GetMapping("activity/{activity}")
    public ResponseEntity getEventsByActivity(@PathVariable String activity){return new ResponseEntity(eventService.getAllEventsByActivity(activity),HttpStatus.ACCEPTED);}


    @PostMapping("new")
    public ResponseEntity addEvent(@RequestBody EventEntity eventEntity) {
        eventService.saveData(eventEntity);
        return ResponseEntity.ok().build();
    }
}
