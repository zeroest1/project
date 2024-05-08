package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "participants")
@Getter
@Setter
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName; // Nullable, for individuals
    private String lastName;  // Nullable, for individuals
    private String companyLegalName; // Nullable, for companies
    private String registrationCode; // Could be personal or company registration code
    private Integer participantCount; // Relevant for companies
    private String paymentMethod;

    @Column(length = 1500)  // Optional: Specify column definitions
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

}
