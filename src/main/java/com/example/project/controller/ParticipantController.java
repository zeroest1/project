package com.example.project.controller;

import com.example.project.dto.CompanyDTO;
import com.example.project.dto.IndividualDTO;
import com.example.project.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    // Individual CRUD Operations
    @GetMapping("/individuals/{eventId}")
    public List<IndividualDTO> getAllIndividuals(@PathVariable Long eventId) {
        return participantService.getAllIndividualsByEvent(eventId);
    }

    @PostMapping("/individuals/{eventId}")
    public void createIndividual(@PathVariable Long eventId, @RequestBody IndividualDTO individual) {
        participantService.saveIndividual(eventId, individual);
    }

    @GetMapping("/individuals/details/{individualId}")
    public IndividualDTO getIndividualDetails(@PathVariable Long individualId) {
        return participantService.getIndividualById(individualId);
    }

    @PutMapping("/individuals/update/{individualId}")
    public void updateIndividual(@PathVariable Long individualId, @RequestBody IndividualDTO individual) {
        participantService.updateIndividual(individualId, individual);
    }

    @DeleteMapping("/individuals/{individualId}")
    public void deleteIndividual(@PathVariable Long individualId) {
        participantService.deleteIndividual(individualId);
    }

    // Company CRUD Operations
    @GetMapping("/companies/{eventId}")
    public List<CompanyDTO> getAllCompanies(@PathVariable Long eventId) {
        return participantService.getAllCompaniesByEvent(eventId);
    }

    @PostMapping("/companies/{eventId}")
    public void createCompany(@PathVariable Long eventId, @RequestBody CompanyDTO company) {
        participantService.saveCompany(eventId, company);
    }

    @GetMapping("/companies/details/{companyId}")
    public CompanyDTO getCompanyDetails(@PathVariable Long companyId) {
        return participantService.getCompanyById(companyId);
    }

    @PutMapping("/companies/update/{companyId}")
    public void updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO company) {
        participantService.updateCompany(companyId, company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void deleteCompany(@PathVariable Long companyId) {
        participantService.deleteCompany(companyId);
    }
}
