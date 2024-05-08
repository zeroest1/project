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
}
