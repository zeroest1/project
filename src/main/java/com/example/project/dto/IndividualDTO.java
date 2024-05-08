package com.example.project.dto;

import lombok.Data;

@Data
public class IndividualDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalId;
    private String paymentMethod;
    private String additionalInfo;
    private Long eventId;
}
