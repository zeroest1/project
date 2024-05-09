package com.example.project.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * EventDTO is a data transfer object that represents an event.
 * It is used to transfer event data between the service layer and clients.
 */
@Data
public class EventDTO {
    private Long id; // Unique identifier for the event.
    private String name; // Name of the event.
    private String location; // Location where the event is held.
    private LocalDateTime time; // Date and time of the event.
    private String additionalInfo; // Additional information about the event.
    private int participantCount; // Total number of participants in the event.
    private List<IndividualDTO> individuals; // List of individuals participating in the event.
    private List<CompanyDTO> companies; // List of companies participating in the event.
}
