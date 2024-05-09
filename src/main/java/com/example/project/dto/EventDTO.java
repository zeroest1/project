package com.example.project.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private String location;
    private LocalDateTime time;
    private String additionalInfo;
    private int participantCount;
    private List<IndividualDTO> individuals;
    private List<CompanyDTO> companies;
}
