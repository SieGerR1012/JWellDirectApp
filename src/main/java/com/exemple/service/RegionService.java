package com.exemple.service;

import com.exemple.model.Region;
import com.exemple.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }

    public List<Region> findAll() {
        return repository.findAll();
    }

    public Region findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
    }

    public Region create(Region region) {
        return repository.save(region);
    }

    public Region update(Long id, Region region) {
        Region existing = findById(id);
        existing.setName(region.getName());
        return repository.save(existing);
    }

    public Region patch(Long id, Region region) {
        Region existing = findById(id);
        if (region.getName() != null) {
            existing.setName(region.getName());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        Region existing = findById(id);
        repository.delete(existing);
    }
}
