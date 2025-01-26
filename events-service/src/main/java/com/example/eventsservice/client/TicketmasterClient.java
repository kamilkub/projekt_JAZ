package com.example.eventsservice.client;

import com.example.eventsdata.entity.Event;
import com.example.eventsdata.repository.EventRepository;
import com.example.eventsservice.dto.EventDTO;
import com.example.eventsservice.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketmasterClient {

    private final RestTemplate restTemplate;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Value("${ticketmaster.api.url}")
    private String apiUrl;

    @Value("${ticketmaster.api.key}")
    private String apiKey;

    public TicketmasterClient(RestTemplate restTemplate, EventRepository eventRepository, EventMapper eventMapper) {
        this.restTemplate = restTemplate;
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public void fetchAndSaveEvents() {
        String url = String.format("%s/events.json?countryCode=PL&apikey=%s", apiUrl, apiKey);
        TicketmasterResponse response = restTemplate.getForObject(url, TicketmasterResponse.class);

        if (response != null && response.getEmbedded() != null) {
            List<EventDTO> eventDTOs = response.getEmbedded().getEvents().stream()
                    .map(this::convertToEventDTO)
                    .collect(Collectors.toList());

            List<Event> events = eventDTOs.stream()
                    .map(eventMapper::toEntity)
                    .collect(Collectors.toList());

            eventRepository.saveAll(events);
        }
    }

    private EventDTO convertToEventDTO(TicketmasterEvent ticketmasterEvent) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName(ticketmasterEvent.getName());
        eventDTO.setDate(ticketmasterEvent.getDates().getStart().getLocalDate());
        eventDTO.setTicketUrl(ticketmasterEvent.getUrl());

        if (ticketmasterEvent.getEmbedded() != null) {
            if (ticketmasterEvent.getEmbedded().getVenues() != null && !ticketmasterEvent.getEmbedded().getVenues().isEmpty()) {
                eventDTO.setVenueName(ticketmasterEvent.getEmbedded().getVenues().get(0).getName());
            }
            if (ticketmasterEvent.getEmbedded().getAttractions() != null && !ticketmasterEvent.getEmbedded().getAttractions().isEmpty()) {
                eventDTO.setGenreName(ticketmasterEvent.getEmbedded().getAttractions().get(0).getClassifications().get(0).getGenre().getName());
            }
        }

        return eventDTO;
    }
}
