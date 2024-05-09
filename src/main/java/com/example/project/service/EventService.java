package com.example.project.service;

import com.example.project.dto.EventDTO;
import com.example.project.entity.Event;
import com.example.project.mapper.EventMapper;
import com.example.project.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Event entities.
 */
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    /**
     * Retrieves all events.
     * @return A list of EventDTOs representing all events.
     */
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.toDtoList(events);
    }
    /**
     * Saves an event.
     * @param eventDTO The EventDTO object to save.
     */
    public void saveEvent(EventDTO eventDTO) {
        eventRepository.save(eventMapper.eventDtoToEvent(eventDTO));
    }
    /**
     * Deletes an event by its ID.
     * @param id The ID of the event to delete.
     */
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    /**
     * Increases the participant count of an event by the specified amount.
     * @param id The ID of the event.
     * @param count The amount by which to increase the participant count.
     */
    @Transactional
    public void increaseParticipantCount(Long id, int count) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            int newCount = event.getParticipantCount() + count;
            event.setParticipantCount(newCount);
            eventRepository.save(event);
        } else {
            // Handle the case where the event does not exist
            throw new IllegalArgumentException("No event found with ID: " + id);
        }
    }
    /**
     * Decreases the participant count of an event by the specified amount.
     * @param id The ID of the event.
     * @param count The amount by which to decrease the participant count.
     */
    @Transactional
    public void decreaseParticipantCount(Long id, int count) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            int currentCount = event.getParticipantCount();
            if (count > currentCount) {
                // Handle the case where the decrease is more than the current count
                throw new IllegalArgumentException("Cannot decrease participants below zero");
            }
            int newCount = currentCount - count;
            event.setParticipantCount(newCount);
            eventRepository.save(event);
        } else {
            // Handle the case where the event does not exist
            throw new IllegalArgumentException("No event found with ID: " + id);
        }
    }
    /**
     * Retrieves an event by its ID.
     * @param id The ID of the event.
     * @return The EventDTO object representing the event, or null if not found.
     */
    public EventDTO getEvent(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        return eventOptional.map(eventMapper::eventToEventDto).orElse(null);
    }
}
