package com.example.project.controller;

import com.example.project.dto.EventDTO;
import com.example.project.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The EventController class handles all web requests for managing events.
 * It uses EventService to delegate core business actions like retrieving, adding, and deleting events.
 * It is annotated as a RestController, making it suitable for creating RESTful endpoints.
 */
@RestController
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    /**
     * Constructs an EventController with the necessary dependency.
     *
     * @param eventService The service used for event operations. This dependency is automatically injected.
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves a list of all events.
     *
     * @return a list of {@link EventDTO} objects representing all events currently available.
     */
    @GetMapping("/events")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    /**
     * Retrieves a single event by its ID.
     *
     * @param id the unique identifier of the event to retrieve.
     * @return an {@link EventDTO} representing the event, or null if the event does not exist.
     */
    @GetMapping("/events/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    /**
     * Creates a new event based on the provided EventDTO object.
     *
     * @param event the {@link EventDTO} containing the event data to be created.
     */
    @PostMapping("/events")
    public void createEvent(@RequestBody EventDTO event) {
        eventService.saveEvent(event);
    }

    /**
     * Deletes an existing event by its ID.
     *
     * @param id the unique identifier of the event to delete.
     */
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
