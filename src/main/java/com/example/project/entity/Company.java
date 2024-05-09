package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a company participating in events.
 */
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {

    /**
     * The unique identifier for the company.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the company.
     */
    private String companyName;

    /**
     * The registration code of the company.
     */
    @Column(name = "registration_code")
    private String companyRegistrationCode;

    /**
     * The number of participants from the company.
     */
    @Column(name = "participant_count")
    private Integer numberOfParticipants;

    /**
     * The payment method used by the company.
     */
    private String paymentMethod;

    /**
     * Additional information about the company.
     */
    @Column(length = 5000)
    private String additionalInfo;

    /**
     * The event in which the company is participating.
     */
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
