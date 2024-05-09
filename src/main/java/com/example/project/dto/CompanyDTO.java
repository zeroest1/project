package com.example.project.dto;

import lombok.Data;

/**
 * CompanyDTO is a data transfer object used to carry company data between processes.
 * It encapsulates details about a company that may participate in an event.
 */
@Data
public class CompanyDTO {
    private Long id; // Unique identifier for the company.
    private String companyName; // Name of the company.
    private String companyRegistrationCode; // Registration code of the company.
    private Integer numberOfParticipants; // Number of participants from this company.
    private String paymentMethod; // Payment method used by the company for the event.
    private String additionalInfo; // Any additional information related to the company.
    private Long eventId; // The event ID to which the company is linked.
}
