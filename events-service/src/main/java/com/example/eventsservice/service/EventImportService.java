package com.example.eventsservice.service;

import com.example.eventsdata.entity.Event;
import com.example.eventsservice.client.TicketmasterClient;
import com.example.eventsservice.dto.EventDTO;
import com.example.eventsservice.mapper.EventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventImportService {

    private final TicketmasterClient ticketmasterClient;
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventImportService(TicketmasterClient ticketmasterClient, EventService eventService, EventMapper eventMapper) {
        this.ticketmasterClient = ticketmasterClient;
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    public void importEvents() {
//        List<EventDTO> events = ticketmasterClient.getEventsInPoland();
//        for (EventDTO eventDTO : events) {
//            Event event = eventMapper.toEntity(eventDTO);
//            eventService.createEvent(eventMapper.toDto(event));
//        }
    }
}
