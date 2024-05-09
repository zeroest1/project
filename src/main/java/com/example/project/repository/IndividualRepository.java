package com.example.project.repository;

import com.example.project.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing and managing Individual entities in the database.
 */
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    /**
     * Finds all individuals associated with a specific event ID.
     * @param id The ID of the event.
     * @return A list of individuals associated with the event ID.
     */
    List<Individual> findAllByEvent_Id(Long id);
}