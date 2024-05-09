package com.example.project.service;

import com.example.project.dto.EventDTO;
import com.example.project.entity.Event;
import com.example.project.mapper.EventMapper;
import com.example.project.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEvents_ShouldReturnEventList() {
        // Arrange
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = Arrays.asList(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);
        when(eventMapper.toDtoList(events)).thenReturn(Arrays.asList(new EventDTO(), new EventDTO()));

        // Act
        List<EventDTO> result = eventService.getAllEvents();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventRepository).findAll();
        verify(eventMapper).toDtoList(events);
    }

    @Test
    void saveEvent_ShouldSaveEvent() {
        // Arrange
        EventDTO eventDTO = new EventDTO(); // Assume necessary setters
        Event event = new Event();
        when(eventMapper.eventDtoToEvent(eventDTO)).thenReturn(event);

        // Act
        eventService.saveEvent(eventDTO);

        // Assert
        verify(eventRepository).save(event);
        verify(eventMapper).eventDtoToEvent(eventDTO);
    }

    @Test
    void deleteEvent_ShouldDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        eventService.deleteEvent(id);

        // Assert
        verify(eventRepository).deleteById(id);
    }
    @Test
    void increaseParticipantCount_ShouldIncreaseCount() {
        // Arrange
        Long eventId = 1L;
        Event event = new Event();
        event.setParticipantCount(5);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // Act
        eventService.increaseParticipantCount(eventId, 3);

        // Assert
        assertEquals(8, event.getParticipantCount());
        verify(eventRepository).save(event);
    }

    @Test
    void decreaseParticipantCount_ShouldDecreaseCount() {
        // Arrange
        Long eventId = 1L;
        Event event = new Event();
        event.setParticipantCount(5);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // Act
        eventService.decreaseParticipantCount(eventId, 2);

        // Assert
        assertEquals(3, event.getParticipantCount());
        verify(eventRepository).save(event);
    }

    @Test
    void getEvent_ShouldReturnEventDTO() {
        // Arrange
        Long eventId = 1L;
        Event event = new Event();
        EventDTO eventDTO = new EventDTO();
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventMapper.eventToEventDto(event)).thenReturn(eventDTO);

        // Act
        EventDTO result = eventService.getEvent(eventId);

        // Assert
        assertNotNull(result);
        assertEquals(eventDTO, result);
        verify(eventRepository).findById(eventId);
        verify(eventMapper).eventToEventDto(event);
    }

    @Test
    void getEvent_ShouldHandleNotFound() {
        // Arrange
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // Act
        EventDTO result = eventService.getEvent(eventId);

        // Assert
        assertNull(result);
        verify(eventRepository).findById(eventId);
        verify(eventMapper, never()).eventToEventDto(any(Event.class));
    }

    @Test
    void increaseParticipantCount_ShouldHandleNotFound() {
        /* Arrange */
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> eventService.increaseParticipantCount(eventId, 3));
    }

    @Test
    void decreaseParticipantCount_ShouldHandleNotFound() {
        // Arrange
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> eventService.decreaseParticipantCount(eventId, 2));
    }
}