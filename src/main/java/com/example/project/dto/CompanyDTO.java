package com.example.project.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String companyRegistrationCode;
    private Integer numberOfParticipants;
    private String paymentMethod;
    private String additionalInfo;
    private Long eventId;
}
