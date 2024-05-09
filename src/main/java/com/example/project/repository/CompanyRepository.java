package com.example.project.repository;

import com.example.project.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing and managing Company entities in the database.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Finds all companies associated with a specific event ID.
     * @param id The ID of the event.
     * @return A list of companies associated with the event ID.
     */
    List<Company> findAllByEvent_Id(Long id);
}
