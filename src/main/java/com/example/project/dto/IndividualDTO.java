package com.example.project.dto;

import lombok.Data;

/**
 * IndividualDTO is a data transfer object used to carry individual participant data between processes.
 * It encapsulates details about an individual participant in an event.
 */
@Data
public class IndividualDTO {
    private Long id; // Unique identifier for the individual.
    private String firstName; // First name of the individual.
    private String lastName; // Last name of the individual.
    private String personalId; // Personal identification number of the individual.
    private String paymentMethod; // Payment method used by the individual for the event.
    private String additionalInfo; // Any additional information related to the individual.
    private Long eventId; // The event ID to which the individual is linked.
}
