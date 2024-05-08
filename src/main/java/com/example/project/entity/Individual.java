package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "individuals")
@Getter
@Setter
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String personalId;
    private String paymentMethod;
    @Column(length = 1500)
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
