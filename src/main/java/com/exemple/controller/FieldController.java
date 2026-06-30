package com.exemple.controller;

import com.exemple.model.Field;
import com.exemple.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    private final FieldService service;

    public FieldController(FieldService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Field>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Field> create(@RequestBody Field field) {
        Field created = service.create(field);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> update(@PathVariable Long id, @RequestBody Field field) {
        return ResponseEntity.ok(service.update(id, field));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Field> patch(@PathVariable Long id, @RequestBody Field field) {
        return ResponseEntity.ok(service.patch(id, field));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
