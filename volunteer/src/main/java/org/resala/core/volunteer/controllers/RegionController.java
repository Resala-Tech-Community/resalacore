package org.resala.core.volunteer.controllers;

import org.resala.core.volunteer.entities.RegionEntity;
import org.resala.core.volunteer.services.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(final RegionService service) {
        regionService = service;
    }

    @GetMapping("all")
    public ResponseEntity getVolunteers() {
        return new ResponseEntity<>(regionService.getAllRegions(), HttpStatus.ACCEPTED);
    }

    @PostMapping("new")
    public ResponseEntity addRegion(@RequestBody RegionEntity regionEntity) {
        regionService.saveData(regionEntity);
        return ResponseEntity.ok().build();
    }
}
