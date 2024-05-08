package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @Column(name = "registration_code")
    private String companyRegistrationCode;
    @Column(name = "participant_count")
    private Integer numberOfParticipants;
    private String paymentMethod;
    @Column(length = 5000)
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
