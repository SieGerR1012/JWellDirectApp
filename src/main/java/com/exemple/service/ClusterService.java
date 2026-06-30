package com.exemple.service;

import com.exemple.model.Cluster;
import com.exemple.repository.ClusterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClusterService {

    private final ClusterRepository repository;

    public ClusterService(ClusterRepository repository) {
        this.repository = repository;
    }

    public List<Cluster> findAll() {
        return repository.findAll();
    }

    public Cluster findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cluster not found with id: " + id));
    }

    public Cluster create(Cluster cluster) {
        return repository.save(cluster);
    }

    public Cluster update(Long id, Cluster cluster) {
        Cluster existing = findById(id);
        existing.setName(cluster.getName());
        return repository.save(existing);
    }

    public Cluster patch(Long id, Cluster cluster) {
        Cluster existing = findById(id);
        if (cluster.getName() != null) {
            existing.setName(cluster.getName());
        }
        return repository.save(existing);
    }

    public void delete(Long id) {
        Cluster existing = findById(id);
        repository.delete(existing);
    }
}
