package com.example.project.service;

import com.example.project.dto.CompanyDTO;
import com.example.project.dto.IndividualDTO;
import com.example.project.entity.Company;
import com.example.project.entity.Individual;
import com.example.project.mapper.CompanyMapper;
import com.example.project.mapper.IndividualMapper;
import com.example.project.repository.CompanyRepository;
import com.example.project.repository.EventRepository;
import com.example.project.repository.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final CompanyRepository companyRepository;
    private final IndividualRepository individualRepository;
    private final IndividualMapper individualMapper;
    private final CompanyMapper companyMapper;
    private final EventRepository eventRepository;
    private final EventService eventService;


    public void saveIndividual(Long id, IndividualDTO participant) {
        Individual individual = individualMapper.individualDTOToIndividual(participant);
        individual.setEvent(eventRepository.findById(id).get());
        individualRepository.save(individual);
        eventService.increaseParticipantCount(id, 1);
    }

    public void saveCompany(Long id, CompanyDTO participant) {
        Company company = companyMapper.companyDTOToCompany(participant);
        company.setEvent(eventRepository.findById(id).get());
        companyRepository.save(company);
        eventService.increaseParticipantCount(id, company.getNumberOfParticipants());
    }

    public List<IndividualDTO> getAllIndividualsByEvent(Long eventId) {
        return individualMapper.individualsToIndividualDTOs(individualRepository.findAllByEvent_Id(eventId));
    }

    public IndividualDTO getIndividualById(Long individualId) {
        return individualMapper.individualToIndividualDTO(individualRepository.findById(individualId).get());
    }

    public void updateIndividual(Long individualId, IndividualDTO individual) {
        Individual entity = individualRepository.findById(individualId).get();
        entity.setFirstName(individual.getFirstName());
        entity.setLastName(individual.getLastName());
        entity.setPersonalId(individual.getPersonalId());
        entity.setPaymentMethod(individual.getPaymentMethod());
        entity.setAdditionalInfo(individual.getAdditionalInfo());
        individualRepository.save(entity);
    }

    public List<CompanyDTO> getAllCompaniesByEvent(Long eventId) {
        return companyMapper.companyListToCompanyDTOList(companyRepository.findAllByEvent_Id(eventId));
    }

    public CompanyDTO getCompanyById(Long companyId) {
        return companyMapper.companyToCompanyDTO(companyRepository.findById(companyId).get());
    }

    public void updateCompany(Long companyId, CompanyDTO company) {
        Company entity = companyRepository.findById(companyId).get();
        entity.setCompanyName(company.getCompanyName());
        entity.setAdditionalInfo(company.getAdditionalInfo());
        entity.setPaymentMethod(company.getPaymentMethod());
        entity.setCompanyRegistrationCode(company.getCompanyRegistrationCode());

        int amount = entity.getNumberOfParticipants();
        entity.setNumberOfParticipants(company.getNumberOfParticipants());
        Long eventId = entity.getEvent().getId();
        companyRepository.save(entity);
        eventService.increaseParticipantCount(eventId, entity.getNumberOfParticipants() - amount);
    }

    public void deleteIndividual(Long individualId) {
        Individual individual = individualRepository.findById(individualId).get();
        Long eventId = individual.getEvent().getId();
        individual.setEvent(null);
        individualRepository.save(individual);
        eventService.decreaseParticipantCount(eventId, 1);
    }

    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        Long eventId = company.getEvent().getId();
        company.setEvent(null);
        companyRepository.save(company);
        eventService.decreaseParticipantCount(eventId, company.getNumberOfParticipants());
    }
}