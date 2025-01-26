package com.example.eventsservice.service;

import com.example.eventsservice.dto.EventDTO;
import java.util.List;

public interface IEventService {
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO getEventById(Long id);
    List<EventDTO> getAllEvents();
    EventDTO updateEvent(Long id, EventDTO eventDTO);
    void deleteEvent(Long id);
}
