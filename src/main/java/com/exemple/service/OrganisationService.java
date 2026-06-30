package com.exemple.service;

import com.exemple.model.Organisation;
import com.exemple.repository.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {

    private final OrganisationRepository repository;

    public OrganisationService(OrganisationRepository repository) {
        this.repository = repository;
    }

    public List<Organisation> findAll() {
        return repository.findAll();
    }

    public Organisation findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organisation not found with id: " + id));
    }

    public Organisation create(Organisation organisation) {
        return repository.save(organisation);
    }

    public Organisation update(Long id, Organisation organisation) {
        Organisation existing = findById(id);
        existing.setName(organisation.getName());
        return repository.save(existing);
    }

    public Organisation patch(Long id, Organisation organisation) {
        Organisation existing = findById(id);
        if (organisation.getName() != null) {
            existing.setName(organisation.getName());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        Organisation existing = findById(id);
        repository.delete(existing);
    }
}
