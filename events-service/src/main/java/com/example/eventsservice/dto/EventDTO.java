package com.example.eventsservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class EventDTO {
    private Long id;
    private String name;
    private String date;
    private String ticketUrl;
    private Long venueId;
    private Long genreId;
    private PriceRangeDTO priceRange;
    private Set<Long> attractionIds;
}
