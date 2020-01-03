package org.resala.core.controllers;

import org.resala.core.entities.VolunteerEntity;
import org.resala.core.services.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(final VolunteerService service) {
        volunteerService = service;
    }

    @GetMapping("all")
    public ResponseEntity getVolunteers() {
        return new ResponseEntity(volunteerService.getAllVolunteers(), HttpStatus.ACCEPTED);
    }

    @PostMapping("new")
    public ResponseEntity addVolunteer(@RequestBody VolunteerEntity volunteerEntity) {
        volunteerService.saveData(volunteerEntity);
        return ResponseEntity.ok().build();
    }
}
