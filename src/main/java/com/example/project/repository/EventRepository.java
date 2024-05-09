package com.example.project.repository;

import com.example.project.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing Event entities in the database.
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    // This repository inherits all the methods from JpaRepository
}
