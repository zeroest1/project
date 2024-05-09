package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an individual participant in events.
 */
@Entity
@Table(name = "individuals")
@Getter
@Setter
public class Individual {

    /**
     * The unique identifier for the individual.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the individual.
     */
    private String firstName;

    /**
     * The last name of the individual.
     */
    private String lastName;

    /**
     * The personal identification of the individual.
     */
    private String personalId;

    /**
     * The payment method used by the individual.
     */
    private String paymentMethod;

    /**
     * Additional information about the individual.
     */
    @Column(length = 1500)
    private String additionalInfo;

    /**
     * The event in which the individual is participating.
     */
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
