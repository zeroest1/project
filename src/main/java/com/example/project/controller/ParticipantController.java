package com.example.project.controller;

import com.example.project.dto.CompanyDTO;
import com.example.project.dto.IndividualDTO;
import com.example.project.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ParticipantController class handles all web requests for managing individual and company participants associated with events.
 * It uses ParticipantService to delegate core business actions like retrieving, adding, updating, and deleting participants.
 * It is annotated as a RestController, making it suitable for creating RESTful endpoints focused on participant management.
 */
@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    /**
     * Constructs a ParticipantController with the necessary dependency on ParticipantService.
     *
     * @param participantService The service used for participant operations. This dependency is automatically injected.
     */
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    /**
     * Retrieves all individuals associated with a specific event.
     *
     * @param eventId The ID of the event to find all associated individuals.
     * @return a list of {@link IndividualDTO} objects representing all individuals participating in the specified event.
     */
    @GetMapping("/individuals/{eventId}")
    public List<IndividualDTO> getAllIndividuals(@PathVariable Long eventId) {
        return participantService.getAllIndividualsByEvent(eventId);
    }

    /**
     * Creates a new individual and adds them to a specific event.
     *
     * @param eventId The ID of the event where the individual will be added.
     * @param individual The {@link IndividualDTO} containing the individual data to be created and added.
     */
    @PostMapping("/individuals/{eventId}")
    public void createIndividual(@PathVariable Long eventId, @RequestBody IndividualDTO individual) {
        participantService.saveIndividual(eventId, individual);
    }

    /**
     * Retrieves details of a specific individual by their ID.
     *
     * @param individualId The unique identifier of the individual to retrieve.
     * @return an {@link IndividualDTO} representing the individual, or null if the individual does not exist.
     */
    @GetMapping("/individuals/details/{individualId}")
    public IndividualDTO getIndividualDetails(@PathVariable Long individualId) {
        return participantService.getIndividualById(individualId);
    }

    /**
     * Updates an existing individual's details.
     *
     * @param individualId The ID of the individual to update.
     * @param individual The {@link IndividualDTO} containing the updated data for the individual.
     */
    @PutMapping("/individuals/update/{individualId}")
    public void updateIndividual(@PathVariable Long individualId, @RequestBody IndividualDTO individual) {
        participantService.updateIndividual(individualId, individual);
    }

    /**
     * Deletes an individual from an event by their ID.
     *
     * @param individualId The ID of the individual to delete.
     */
    @DeleteMapping("/individuals/{individualId}")
    public void deleteIndividual(@PathVariable Long individualId) {
        participantService.deleteIndividual(individualId);
    }

    /**
     * Retrieves all companies associated with a specific event.
     *
     * @param eventId The ID of the event to find all associated companies.
     * @return a list of {@link CompanyDTO} objects representing all companies participating in the specified event.
     */
    @GetMapping("/companies/{eventId}")
    public List<CompanyDTO> getAllCompanies(@PathVariable Long eventId) {
        return participantService.getAllCompaniesByEvent(eventId);
    }

    /**
     * Creates a new company and adds it to a specific event.
     *
     * @param eventId The ID of the event where the company will be added.
     * @param company The {@link CompanyDTO} containing the company data to be created and added.
     */
    @PostMapping("/companies/{eventId}")
    public void createCompany(@PathVariable Long eventId, @RequestBody CompanyDTO company) {
        participantService.saveCompany(eventId, company);
    }

    /**
     * Retrieves details of a specific company by their ID.
     *
     * @param companyId The unique identifier of the company to retrieve.
     * @return a {@link CompanyDTO} representing the company, or null if the company does not exist.
     */
    @GetMapping("/companies/details/{companyId}")
    public CompanyDTO getCompanyDetails(@PathVariable Long companyId) {
        return participantService.getCompanyById(companyId);
    }

    /**
     * Updates an existing company's details.
     *
     * @param companyId The ID of the company to update.
     * @param company The {@link CompanyDTO} containing the updated data for the company.
     */
    @PutMapping("/companies/update/{companyId}")
    public void updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO company) {
        participantService.updateCompany(companyId, company);
    }

    /**
     * Deletes a company from an event by their ID.
     *
     * @param companyId The ID of the company to delete.
     */
    @DeleteMapping("/companies/{companyId}")
    public void deleteCompany(@PathVariable Long companyId) {
        participantService.deleteCompany(companyId);
    }
}

