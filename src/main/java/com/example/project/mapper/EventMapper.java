package com.example.project.mapper;

import com.example.project.dto.EventDTO;
import com.example.project.entity.Event;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Mapper interface for converting between Event and EventDTO objects.
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * Converts an Event object to an EventDTO object.
     * @param event The Event object to convert.
     * @return The converted EventDTO object.
     */
    EventDTO eventToEventDto(Event event);

    /**
     * Converts an EventDTO object to an Event object.
     * @param eventDto The EventDTO object to convert.
     * @return The converted Event object.
     */
    Event eventDtoToEvent(EventDTO eventDto);

    /**
     * Converts a list of Event objects to a list of EventDTO objects.
     * @param events The list of Event objects to convert.
     * @return The converted list of EventDTO objects.
     */
    List<EventDTO> toDtoList(List<Event> events);
}