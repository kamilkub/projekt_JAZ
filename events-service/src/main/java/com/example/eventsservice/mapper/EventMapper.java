package com.example.eventsservice.mapper;

import com.example.eventsdata.entity.Event;
import com.example.eventsservice.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDto(Event event);
    Event toEntity(EventDTO eventDTO);
    void updateEntityFromDto(EventDTO eventDTO, @MappingTarget Event event);
}
