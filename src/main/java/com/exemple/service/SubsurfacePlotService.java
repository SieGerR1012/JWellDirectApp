package com.exemple.service;

import com.exemple.model.SubsurfacePlot;
import com.exemple.repository.SubsurfacePlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsurfacePlotService {

    private final SubsurfacePlotRepository repository;

    public SubsurfacePlotService(SubsurfacePlotRepository repository) {
        this.repository = repository;
    }

    public List<SubsurfacePlot> findAll() {
        return repository.findAll();
    }

    public SubsurfacePlot findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubsurfacePlot not found with id: " + id));
    }

    public SubsurfacePlot create(SubsurfacePlot subsurfacePlot) {
        return repository.save(subsurfacePlot);
    }

    public SubsurfacePlot update(Long id, SubsurfacePlot subsurfacePlot) {
        SubsurfacePlot existing = findById(id);
        existing.setName(subsurfacePlot.getName());
        return repository.save(existing);
    }

    public SubsurfacePlot patch(Long id, SubsurfacePlot subsurfacePlot) {
        SubsurfacePlot existing = findById(id);
        if (subsurfacePlot.getName() != null) {
            existing.setName(subsurfacePlot.getName());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        SubsurfacePlot existing = findById(id);
        repository.delete(existing);
    }
}
