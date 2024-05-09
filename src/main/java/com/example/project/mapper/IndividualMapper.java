package com.example.project.mapper;

import com.example.project.dto.IndividualDTO;
import com.example.project.entity.Individual;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between Individual and IndividualDTO objects.
 */
@Mapper(componentModel = "spring")
public interface IndividualMapper {

    /**
     * Converts an Individual object to an IndividualDTO object.
     * @param individual The Individual object to convert.
     * @return The converted IndividualDTO object.
     */
    IndividualDTO individualToIndividualDTO(Individual individual);

    /**
     * Converts an IndividualDTO object to an Individual object.
     * @param individualDTO The IndividualDTO object to convert.
     * @return The converted Individual object.
     */
    Individual individualDTOToIndividual(IndividualDTO individualDTO);

    /**
     * Converts a list of Individual objects to a list of IndividualDTO objects.
     * @param individuals The list of Individual objects to convert.
     * @return The converted list of IndividualDTO objects.
     */
    List<IndividualDTO> individualsToIndividualDTOs(List<Individual> individuals);
}
