package com.exemple.controller;

import com.exemple.model.DrillingCrew;
import com.exemple.service.DrillingCrewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drilling-crews")
public class DrillingCrewController {

    private final DrillingCrewService service;

    public DrillingCrewController(DrillingCrewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DrillingCrew>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrillingCrew> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DrillingCrew> create(@RequestBody DrillingCrew drillingCrew) {
        DrillingCrew created = service.create(drillingCrew);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrillingCrew> update(@PathVariable Long id, @RequestBody DrillingCrew drillingCrew) {
        return ResponseEntity.ok(service.update(id, drillingCrew));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DrillingCrew> patch(@PathVariable Long id, @RequestBody DrillingCrew drillingCrew) {
        return ResponseEntity.ok(service.patch(id, drillingCrew));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
