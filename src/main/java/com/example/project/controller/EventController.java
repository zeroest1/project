package com.example.project.controller;

import com.example.project.dto.EventDTO;
import com.example.project.entity.Event;
import com.example.project.mapper.EventMapper;
import com.example.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return events.stream().map(EventMapper.INSTANCE::eventToEventDTO).collect(Collectors.toList());
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.eventDTOToEvent(eventDTO);
        event = eventService.saveEvent(event);
        return EventMapper.INSTANCE.eventToEventDTO(event);
    }
}
