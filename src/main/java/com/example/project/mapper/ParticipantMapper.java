package com.example.project.mapper;

import com.example.project.dto.ParticipantDTO;
import com.example.project.entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    ParticipantDTO participantToParticipantDto(Participant participant);
    @Mapping(target = "event", ignore = true)
    Participant participantDtoToParticipant(ParticipantDTO participantDto);
    List<ParticipantDTO> participantListToParticipantDtoList(List<Participant> participantList);
}
