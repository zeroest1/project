package com.example.project.controller;

import com.example.project.dto.ParticipantDTO;
import com.example.project.entity.Participant;
import com.example.project.mapper.ParticipantMapper;
import com.example.project.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public List<ParticipantDTO> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        return participants.stream()
                .map(ParticipantMapper.INSTANCE::participantToParticipantDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ParticipantDTO createParticipant(@RequestBody ParticipantDTO participantDTO) {
        Participant participant = ParticipantMapper.INSTANCE.participantDTOToParticipant(participantDTO);
        participant = participantService.saveParticipant(participant);
        return ParticipantMapper.INSTANCE.participantToParticipantDTO(participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
    }
}
