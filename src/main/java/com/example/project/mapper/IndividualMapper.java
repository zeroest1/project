package com.example.project.mapper;

import com.example.project.dto.IndividualDTO;
import com.example.project.entity.Individual;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndividualMapper {
    IndividualDTO individualToIndividualDTO(Individual individual);
    Individual individualDTOToIndividual(IndividualDTO individualDTO);
    List<IndividualDTO> individualsToIndividualDTOs(List<Individual> individuals);
}
