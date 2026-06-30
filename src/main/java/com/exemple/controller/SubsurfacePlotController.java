package com.exemple.controller;

import com.exemple.model.SubsurfacePlot;
import com.exemple.service.SubsurfacePlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subsurface-plots")
public class SubsurfacePlotController {

    private final SubsurfacePlotService service;

    public SubsurfacePlotController(SubsurfacePlotService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SubsurfacePlot>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubsurfacePlot> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SubsurfacePlot> create(@RequestBody SubsurfacePlot subsurfacePlot) {
        SubsurfacePlot created = service.create(subsurfacePlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubsurfacePlot> update(@PathVariable Long id, @RequestBody SubsurfacePlot subsurfacePlot) {
        return ResponseEntity.ok(service.update(id, subsurfacePlot));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubsurfacePlot> patch(@PathVariable Long id, @RequestBody SubsurfacePlot subsurfacePlot) {
        return ResponseEntity.ok(service.patch(id, subsurfacePlot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
