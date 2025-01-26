package com.example.eventsservice.service;

import com.example.eventsservice.dto.EventDTO;
import com.example.eventsservice.mapper.EventMapper;
import com.example.eventsdata.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        var event = eventMapper.toEntity(eventDTO);
        var savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    @Override
    public EventDTO getEventById(Long id) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return eventMapper.toDto(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        var existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        eventMapper.updateEntityFromDto(eventDTO, existingEvent);
        var updatedEvent = eventRepository.save(existingEvent);
        return eventMapper.toDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
