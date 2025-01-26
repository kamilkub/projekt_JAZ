package com.example.eventsservice.mapper;

import com.example.eventsdata.entity.Event;
import com.example.eventsservice.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(source = "venue.name", target = "venueName")
    @Mapping(source = "genre.name", target = "genreName")
    EventDTO toDto(Event event);

    @Mapping(source = "venueName", target = "venue.name")
    @Mapping(source = "genreName", target = "genre.name")
    Event toEntity(EventDTO eventDTO);

    @Mapping(source = "venueName", target = "venue.name")
    @Mapping(source = "genreName", target = "genre.name")
    void updateEntityFromDto(EventDTO eventDTO, @MappingTarget Event event);
}
