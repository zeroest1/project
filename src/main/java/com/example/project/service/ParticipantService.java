package com.example.project.service;

import com.example.project.dto.CompanyDTO;
import com.example.project.dto.IndividualDTO;
import com.example.project.entity.Company;
import com.example.project.entity.Event;
import com.example.project.entity.Individual;
import com.example.project.mapper.CompanyMapper;
import com.example.project.mapper.IndividualMapper;
import com.example.project.repository.CompanyRepository;
import com.example.project.repository.EventRepository;
import com.example.project.repository.IndividualRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing participant entities.
 */
@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final CompanyRepository companyRepository;
    private final IndividualRepository individualRepository;
    private final IndividualMapper individualMapper;
    private final CompanyMapper companyMapper;
    private final EventRepository eventRepository;
    private final EventService eventService;


    /**
     * Saves an individual participant for a specific event.
     * @param eventId The ID of the event.
     * @param participantDTO The IndividualDTO object representing the participant.
     */
    @Transactional
    public void saveIndividual(Long eventId, IndividualDTO participantDTO) {
        Individual individual = individualMapper.individualDTOToIndividual(participantDTO);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new IllegalArgumentException("No event found with ID: " + eventId);
        }
        individual.setEvent(eventOptional.get());
        individualRepository.save(individual);
        eventService.increaseParticipantCount(eventId, 1);
    }

    /**
     * Saves a company participant for a specific event.
     * @param eventId The ID of the event.
     * @param participantDTO The CompanyDTO object representing the participant.
     */
    @Transactional
    public void saveCompany(Long eventId, CompanyDTO participantDTO) {
        Company company = companyMapper.companyDTOToCompany(participantDTO);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new IllegalArgumentException("No event found with ID: " + eventId);
        }
        company.setEvent(eventOptional.get());
        companyRepository.save(company);
        eventService.increaseParticipantCount(eventId, company.getNumberOfParticipants());
    }

    /**
     * Retrieves all individual participants for a specific event.
     * @param eventId The ID of the event.
     * @return A list of IndividualDTO objects representing the individual participants.
     */
    public List<IndividualDTO> getAllIndividualsByEvent(Long eventId) {
        return individualMapper.individualsToIndividualDTOs(individualRepository.findAllByEvent_Id(eventId));
    }

    /**
     * Retrieves an individual participant by ID.
     * @param individualId The ID of the individual participant.
     * @return The IndividualDTO object representing the individual participant.
     * @throws IllegalArgumentException if no individual is found with the given ID.
     */
    public IndividualDTO getIndividualById(Long individualId) {
        Optional<Individual> individualOptional = individualRepository.findById(individualId);
        if (individualOptional.isEmpty()) {
            throw new IllegalArgumentException("No individual found with ID: " + individualId);
        }
        return individualMapper.individualToIndividualDTO(individualOptional.get());
    }

    /**
     * Updates an individual participant's information.
     * @param individualId The ID of the individual participant to update.
     * @param individualDTO The updated IndividualDTO object.
     * @throws IllegalArgumentException if no individual is found with the given ID.
     */
    @Transactional
    public void updateIndividual(Long individualId, IndividualDTO individualDTO) {
        Optional<Individual> individualOptional = individualRepository.findById(individualId);
        if (individualOptional.isEmpty()) {
            throw new IllegalArgumentException("No individual found with ID: " + individualId);
        }
        Individual entity = individualOptional.get();
        entity.setFirstName(individualDTO.getFirstName());
        entity.setLastName(individualDTO.getLastName());
        entity.setPersonalId(individualDTO.getPersonalId());
        entity.setPaymentMethod(individualDTO.getPaymentMethod());
        entity.setAdditionalInfo(individualDTO.getAdditionalInfo());
        individualRepository.save(entity);
    }

    /**
     * Retrieves all company participants for a specific event.
     * @param eventId The ID of the event.
     * @return A list of CompanyDTO objects representing the company participants.
     */
    public List<CompanyDTO> getAllCompaniesByEvent(Long eventId) {
        return companyMapper.companyListToCompanyDTOList(companyRepository.findAllByEvent_Id(eventId));
    }

    /**
     * Retrieves a company participant by ID.
     * @param companyId The ID of the company participant.
     * @return The CompanyDTO object representing the company participant.
     * @throws IllegalArgumentException if no company is found with the given ID.
     */
    public CompanyDTO getCompanyById(Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) {
            throw new IllegalArgumentException("No company found with ID: " + companyId);
        }
        return companyMapper.companyToCompanyDTO(companyOptional.get());
    }

    /**
     * Updates a company participant's information.
     * @param companyId The ID of the company participant to update.
     * @param companyDTO The updated CompanyDTO object.
     * @throws IllegalArgumentException if no company is found with the given ID.
     */
    @Transactional
    public void updateCompany(Long companyId, CompanyDTO companyDTO) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) {
            throw new IllegalArgumentException("No company found with ID: " + companyId);
        }
        Company entity = companyOptional.get();
        entity.setCompanyName(companyDTO.getCompanyName());
        entity.setAdditionalInfo(companyDTO.getAdditionalInfo());
        entity.setPaymentMethod(companyDTO.getPaymentMethod());
        entity.setCompanyRegistrationCode(companyDTO.getCompanyRegistrationCode());

        int originalCount = entity.getNumberOfParticipants();
        entity.setNumberOfParticipants(companyDTO.getNumberOfParticipants());
        companyRepository.save(entity);

        Long eventId = entity.getEvent().getId();
        eventService.increaseParticipantCount(eventId, entity.getNumberOfParticipants() - originalCount);
    }

    /**
     * Deletes an individual participant by ID.
     * @param individualId The ID of the individual participant to delete.
     * @throws IllegalArgumentException if no individual is found with the given ID.
     */
    @Transactional
    public void deleteIndividual(Long individualId) {
        Optional<Individual> individualOptional = individualRepository.findById(individualId);
        if (individualOptional.isEmpty()) {
            throw new IllegalArgumentException("No individual found with ID: " + individualId);
        }
        Individual individual = individualOptional.get();
        Long eventId = individual.getEvent().getId();
        individual.setEvent(null);
        individualRepository.save(individual);
        eventService.decreaseParticipantCount(eventId, 1);
    }

    /**
     * Deletes a company participant by ID.
     * @param companyId The ID of the company participant to delete.
     * @throws IllegalArgumentException if no company is found with the given ID.
     */
    @Transactional
    public void deleteCompany(Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) {
            throw new IllegalArgumentException("No company found with ID: " + companyId);
        }
        Company company = companyOptional.get();
        Long eventId = company.getEvent().getId();
        company.setEvent(null);
        companyRepository.save(company);
        eventService.decreaseParticipantCount(eventId, company.getNumberOfParticipants());
    }

}