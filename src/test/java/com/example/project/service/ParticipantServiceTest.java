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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceTest {
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private IndividualRepository individualRepository;

    @Mock
    private IndividualMapper individualMapper;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventService eventService;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void testSaveIndividual() {
        // Arrange
        IndividualDTO participantDTO = new IndividualDTO(); // Set properties as needed
        Individual individual = new Individual();
        when(individualMapper.individualDTOToIndividual(any())).thenReturn(individual);
        when(eventRepository.findById(any())).thenReturn(Optional.of(new Event()));

        // Act
        participantService.saveIndividual(1L, participantDTO);

        // Assert
        verify(individualRepository).save(individual);
        verify(eventService).increaseParticipantCount(1L, 1);
    }

    @Test
    void testSaveCompany() {
        // Arrange
        CompanyDTO companyDTO = new CompanyDTO(); // Set properties as needed
        Company company = new Company();
        company.setNumberOfParticipants(10);
        when(companyMapper.companyDTOToCompany(any())).thenReturn(company);
        when(eventRepository.findById(any())).thenReturn(Optional.of(new Event()));

        // Act
        participantService.saveCompany(1L, companyDTO);

        // Assert
        verify(companyRepository).save(company);
        verify(eventService).increaseParticipantCount(1L, 10);
    }

    @Test
    void testGetAllIndividualsByEvent() {
        // Arrange
        List<Individual> individuals = Arrays.asList(new Individual(), new Individual());
        when(individualRepository.findAllByEvent_Id(anyLong())).thenReturn(individuals);
        when(individualMapper.individualsToIndividualDTOs(anyList())).thenReturn(Arrays.asList(new IndividualDTO(), new IndividualDTO()));

        // Act
        List<IndividualDTO> results = participantService.getAllIndividualsByEvent(1L);

        // Assert
        assertEquals(2, results.size());
        verify(individualRepository).findAllByEvent_Id(1L);
        verify(individualMapper).individualsToIndividualDTOs(individuals);
    }


    @Test
    void testGetIndividualById() {
        // Arrange
        Long individualId = 1L; // Example individual ID
        Individual individual = new Individual();
        individual.setId(individualId);
        individual.setFirstName("John");
        individual.setLastName("Doe");

        IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setFirstName("John");
        individualDTO.setLastName("Doe");

        when(individualRepository.findById(individualId)).thenReturn(Optional.of(individual));
        when(individualMapper.individualToIndividualDTO(individual)).thenReturn(individualDTO);

        // Act
        IndividualDTO result = participantService.getIndividualById(individualId);

        // Assert
        assertNotNull(result, "The returned IndividualDTO should not be null.");
        assertEquals("John", result.getFirstName(), "The first name should match.");
        assertEquals("Doe", result.getLastName(), "The last name should match.");

        verify(individualRepository).findById(individualId);
        verify(individualMapper).individualToIndividualDTO(individual);
    }

    @Test
    void testUpdateIndividual() {
        // Arrange
        Individual individual = new Individual();
        when(individualRepository.findById(anyLong())).thenReturn(Optional.of(individual));

        // Act
        IndividualDTO individualDTO = new IndividualDTO(); // Populate with updated data
        participantService.updateIndividual(1L, individualDTO);

        // Assert
        verify(individualRepository).save(individual);
    }

    @Test
    void testGetAllCompaniesByEvent() {
        // Arrange
        Long eventId = 1L;
        Company company1 = new Company(); // Set properties as necessary
        Company company2 = new Company(); // Set properties as necessary
        List<Company> companies = Arrays.asList(company1, company2);
        List<CompanyDTO> expectedDtos = Arrays.asList(new CompanyDTO(), new CompanyDTO());

        when(companyRepository.findAllByEvent_Id(eventId)).thenReturn(companies);
        when(companyMapper.companyListToCompanyDTOList(companies)).thenReturn(expectedDtos);

        // Act
        List<CompanyDTO> result = participantService.getAllCompaniesByEvent(eventId);

        // Assert
        assertEquals(2, result.size(), "The number of returned CompanyDTOs should be equal to the number of companies");
        assertIterableEquals(expectedDtos, result, "The returned list of CompanyDTOs should match the expected list");

        verify(companyRepository).findAllByEvent_Id(eventId);
        verify(companyMapper).companyListToCompanyDTOList(companies);
    }

    @Test
    void testGetCompanyById() {
        // Arrange
        Long companyId = 1L;
        Company company = new Company(); // Set properties as necessary, e.g., company.setCompanyName("Example Inc.");
        CompanyDTO expectedDto = new CompanyDTO(); // Set properties to match what you expect after mapping

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(companyMapper.companyToCompanyDTO(company)).thenReturn(expectedDto);

        // Act
        CompanyDTO result = participantService.getCompanyById(companyId);

        // Assert
        assertNotNull(result, "The returned CompanyDTO should not be null.");
        assertEquals(expectedDto, result, "The returned CompanyDTO should match the expected DTO.");

        verify(companyRepository).findById(companyId);
        verify(companyMapper).companyToCompanyDTO(company);
    }


    @Test
    void testUpdateCompany() {
        // Arrange
        Long companyId = 1L;
        Long eventId = 1L;
        int initialParticipants = 5;
        int updatedParticipants = 1;

        // Creating an event and associating it with the company
        Event event = new Event();
        event.setId(eventId);
        event.setParticipantCount(50); // Example initial participant count

        // Existing company setup
        Company company = new Company();
        company.setId(companyId);
        company.setEvent(event);
        company.setNumberOfParticipants(initialParticipants);

        // Assuming the company is found
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        // Updated DTO
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setNumberOfParticipants(updatedParticipants); // Updated number of participants

        // Act
        participantService.updateCompany(companyId, companyDTO);

        // Assert
        int participantDiff = updatedParticipants - initialParticipants;
        verify(companyRepository).save(company);
        verify(eventService).increaseParticipantCount(eventId, participantDiff);
    }


    @Test
    void testDeleteIndividual() {
        // Arrange
        Individual individual = new Individual();
        Event event = new Event();
        event.setId(1L);
        individual.setEvent(event);
        when(individualRepository.findById(anyLong())).thenReturn(Optional.of(individual));

        // Act
        participantService.deleteIndividual(1L);

        // Assert
        verify(individualRepository).save(individual);
        verify(eventService).decreaseParticipantCount(1L, 1);
    }

    @Test
    void testDeleteCompany() {
        // Arrange
        Company company = new Company();
        Event event = new Event();
        event.setId(1L);
        company.setEvent(event);
        company.setNumberOfParticipants(3);
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        // Act
        participantService.deleteCompany(1L);

        // Assert
        verify(companyRepository).save(company);
        verify(eventService).decreaseParticipantCount(1L, 3);
    }

}