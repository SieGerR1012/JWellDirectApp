package com.exemple.service;

import com.exemple.model.DrillingCrew;
import com.exemple.repository.DrillingCrewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrillingCrewService {

    private final DrillingCrewRepository repository;

    public DrillingCrewService(DrillingCrewRepository repository) {
        this.repository = repository;
    }

    public List<DrillingCrew> findAll() {
        return repository.findAll();
    }

    public DrillingCrew findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DrillingCrew not found with id: " + id));
    }

    public DrillingCrew create(DrillingCrew drillingCrew) {
        return repository.save(drillingCrew);
    }

    public DrillingCrew update(Long id, DrillingCrew drillingCrew) {
        DrillingCrew existing = findById(id);
        existing.setNumber(drillingCrew.getNumber());
        return repository.save(existing);
    }

    public DrillingCrew patch(Long id, DrillingCrew drillingCrew) {
        DrillingCrew existing = findById(id);
        if (drillingCrew.getNumber() != null) {
            existing.setNumber(drillingCrew.getNumber());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        DrillingCrew existing = findById(id);
        repository.delete(existing);
    }
}
