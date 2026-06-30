package com.exemple.repository;

import com.exemple.model.DrillingCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrillingCrewRepository extends JpaRepository<DrillingCrew, Long> {
}
