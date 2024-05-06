package com.example.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDTO {
    private Long id;
    private String name;
    private String identifier;
    private String type;
}
