package com.example.eventsservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EventDTO {
    private Long id;
    private String name;
    private String date;
    private String ticketUrl;
    private GenreDTO genre;
    private VenueDTO venue;
    private List<PriceRangeDTO> priceRange;
    private List<AttractionDTO> attractions;
}
