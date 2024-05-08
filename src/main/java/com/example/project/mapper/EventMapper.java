package com.example.project.mapper;

import com.example.project.dto.EventDTO;
import com.example.project.entity.Event;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO eventToEventDto(Event event);
    Event eventDtoToEvent(EventDTO eventDto);
    List<EventDTO> toDtoList(List<Event> events);
}