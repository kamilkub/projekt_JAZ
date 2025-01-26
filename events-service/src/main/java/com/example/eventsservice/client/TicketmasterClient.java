package com.example.eventsservice.client;

import com.example.eventsdata.entity.Event;
import com.example.eventsdata.repository.EventRepository;
import com.example.eventsservice.client.response.TicketmasterEvent;
import com.example.eventsservice.client.response.TicketmasterResponse;
import com.example.eventsservice.dto.*;
import com.example.eventsservice.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

        if (response.getEmbedded() != null) {
            List<EventDTO> eventDTOs = response.getEmbedded().getEvents().stream()
                    .map(this::convertToEventDTO)
                    .toList();

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
                var venue = ticketmasterEvent.getEmbedded().getVenues().get(0);
                var venueDto = new VenueDTO();
                venueDto.setAddress(venue.getAddress().getLine1());
                venueDto.setCity(venue.getCity().getName());
                venueDto.setName(venue.getName());
                venueDto.setCountry(venue.getCountry().getName());
                eventDTO.setVenue(venueDto);
            }

            if (ticketmasterEvent.getEmbedded().getAttractions() != null && !ticketmasterEvent.getEmbedded().getAttractions().isEmpty()) {
                var genre = ticketmasterEvent.getEmbedded().getAttractions().get(0).getClassifications().get(0).getGenre();
                var genreDto = new GenreDTO();
                genreDto.setName(genre.getName());
                eventDTO.setGenre(genreDto);

                var attractions = ticketmasterEvent.getEmbedded().getAttractions();

                var attractionsList = new ArrayList<AttractionDTO>();
                for(TicketmasterEvent.Attraction attraction : attractions) {
                    var attractionDto = new AttractionDTO();
                    attractionDto.setUrl(attraction.getUrl());
                    attractionDto.setName(attraction.getName());
                    attractionsList.add(attractionDto);
                }

                eventDTO.setAttractions(attractionsList);

            }
        }

        if(ticketmasterEvent.getPriceRanges() != null) {
            var priceRangeElements = new ArrayList<PriceRangeDTO>();
            for(TicketmasterEvent.PriceRange priceRange : ticketmasterEvent.getPriceRanges()) {
                var princeRangeDto = new PriceRangeDTO();
                princeRangeDto.setCurrency(priceRange.getCurrency());
                princeRangeDto.setMinPrice(priceRange.getMin());
                princeRangeDto.setMaxPrice(priceRange.getMax());
                priceRangeElements.add(princeRangeDto);
            }

            eventDTO.setPriceRange(priceRangeElements);
        }
        return eventDTO;
    }
}
