package com.exemple.repository;

import com.exemple.model.SubsurfacePlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsurfacePlotRepository extends JpaRepository<SubsurfacePlot, Long> {
}
