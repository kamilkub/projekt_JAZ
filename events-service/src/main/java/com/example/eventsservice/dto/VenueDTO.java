package com.example.eventsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueDTO {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String country;
}
