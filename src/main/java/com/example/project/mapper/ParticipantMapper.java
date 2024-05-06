package com.example.project.mapper;

import com.example.project.dto.ParticipantDTO;
import com.example.project.entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantMapper {
    ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

    ParticipantDTO participantToParticipantDTO(Participant participant);
    Participant participantDTOToParticipant(ParticipantDTO participantDTO);
}
