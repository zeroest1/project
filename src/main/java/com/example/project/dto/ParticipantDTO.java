package com.example.project.dto;

import lombok.Data;

@Data
public class ParticipantDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String companyLegalName;
    private String registrationCode;
    private Integer participantCount;
    private String paymentMethod;
    private String additionalInfo;
}
