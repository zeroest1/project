package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an event in the system.
 */
@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    /**
     * The unique identifier for the event.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the event.
     */
    @Column(name = "name")
    private String name;

    /**
     * The location of the event.
     */
    @Column(name = "location")
    private String location;

    /**
     * The time of the event.
     */
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    /**
     * Additional information about the event.
     */
    @Column(name = "additional_info", length = 1000)
    private String additionalInfo;

    /**
     * The count of participants registered for the event.
     */
    @Column(name = "participant_count")
    private int participantCount = 0;

    /**
     * The list of individual participants registered for the event.
     */
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Individual> individuals;

    /**
     * The list of companies participating in the event.
     */
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Company> companies;
}

