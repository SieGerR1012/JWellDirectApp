package com.exemple.service;

import com.exemple.model.Field;
import com.exemple.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository repository;

    public FieldService(FieldRepository repository) {
        this.repository = repository;
    }

    public List<Field> findAll() {
        return repository.findAll();
    }

    public Field findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found with id: " + id));
    }

    public Field create(Field field) {
        return repository.save(field);
    }

    public Field update(Long id, Field field) {
        Field existing = findById(id);
        existing.setName(field.getName());
        return repository.save(existing);
    }

    public Field patch(Long id, Field field) {
        Field existing = findById(id);
        if (field.getName() != null) {
            existing.setName(field.getName());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        Field existing = findById(id);
        repository.delete(existing);
    }
}
