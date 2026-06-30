package com.exemple.controller;

import com.exemple.model.Cluster;
import com.exemple.service.ClusterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clusters")
public class ClusterController {

    private final ClusterService service;

    public ClusterController(ClusterService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cluster>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cluster> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cluster> create(@RequestBody Cluster cluster) {
        Cluster created = service.create(cluster);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cluster> update(@PathVariable Long id, @RequestBody Cluster cluster) {
        return ResponseEntity.ok(service.update(id, cluster));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cluster> patch(@PathVariable Long id, @RequestBody Cluster cluster) {
        return ResponseEntity.ok(service.patch(id, cluster));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
