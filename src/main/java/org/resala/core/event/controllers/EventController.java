package org.resala.core.event.controllers;

import org.resala.core.domain.EventEntity;
import org.resala.core.event.dto.EventReqParamDto;
import org.resala.core.event.services.BranchEventService;
import org.resala.core.event.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event")
public class EventController {
    private final EventService eventService;
    private final BranchEventService branchEventService;

    public EventController(final EventService service, final BranchEventService branchEventService) {
        eventService = service;
        this.branchEventService = branchEventService;
    }

    @GetMapping()
    public ResponseEntity getEventsByParams(EventReqParamDto paramDto) {
        return new ResponseEntity<>(branchEventService.findByParam(paramDto), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addEvent(@RequestBody EventEntity eventEntity) {
        eventService.saveData(eventEntity);
        return ResponseEntity.ok().build();
    }
}
