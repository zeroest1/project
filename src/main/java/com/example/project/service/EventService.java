package com.example.project.service;

import com.example.project.dto.EventDTO;
import com.example.project.entity.Event;
import com.example.project.mapper.EventMapper;
import com.example.project.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;


    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.toDtoList(events);
    }

    public void saveEvent(EventDTO eventDTO) {
        eventRepository.save(eventMapper.eventDtoToEvent(eventDTO));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public void increaseParticipantCount(Long id, int count) {
        Event event = eventRepository.findById(id).get();
        event.setParticipantCount(event.getParticipantCount() + count);
        eventRepository.save(event);
    }
    public void decreaseParticipantCount(Long id, int count) {
        Event event = eventRepository.findById(id).get();
        event.setParticipantCount(event.getParticipantCount() - count);
        eventRepository.save(event);
    }

    public EventDTO getEvent(Long id) {
        Event event = eventRepository.findById(id).get();
        return eventMapper.eventToEventDto(event);
    }
}
