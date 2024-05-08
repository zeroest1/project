package com.example.project.repository;

import com.example.project.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
    List<Individual> findAllByEvent_Id(Long id);
}
