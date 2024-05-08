package com.example.project.service;

import com.example.project.dto.ParticipantDTO;
import com.example.project.entity.Participant;
import com.example.project.mapper.ParticipantMapper;
import com.example.project.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;


    public Participant saveParticipant(ParticipantDTO participantDTO) {
        return participantRepository.save(participantMapper.participantDtoToParticipant(participantDTO));
    }

    public List<ParticipantDTO> getAllParticipants() {
        return participantMapper.participantListToParticipantDtoList(participantRepository.findAll());
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}