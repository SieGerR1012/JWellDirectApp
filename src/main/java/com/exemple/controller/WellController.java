package com.exemple.controller;

import com.exemple.model.Well;
import com.exemple.model.WellRequest;
import com.exemple.service.WellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wells")
public class WellController {

    private final WellService service;

    public WellController(WellService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Well>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Well> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Well> create(@RequestBody WellRequest request) {
        Well well = new Well();
        well.setWellNumber(request.getWellNumber());
        Well created = service.create(well,
                request.getOrganisationId(),
                request.getRegionId(),
                request.getSubsurfacePlotId(),
                request.getFieldId(),
                request.getClusterId(),
                request.getDrillingCrewId(),
                request.getCustomerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Well> update(@PathVariable Long id, @RequestBody WellRequest request) {
        Well well = new Well();
        well.setWellNumber(request.getWellNumber());
        Well updated = service.update(id, well,
                request.getOrganisationId(),
                request.getRegionId(),
                request.getSubsurfacePlotId(),
                request.getFieldId(),
                request.getClusterId(),
                request.getDrillingCrewId(),
                request.getCustomerId());
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Well> patch(@PathVariable Long id, @RequestBody WellRequest request) {
        Well well = new Well();
        well.setWellNumber(request.getWellNumber());
        Well updated = service.update(id, well,
                request.getOrganisationId(),
                request.getRegionId(),
                request.getSubsurfacePlotId(),
                request.getFieldId(),
                request.getClusterId(),
                request.getDrillingCrewId(),
                request.getCustomerId());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
