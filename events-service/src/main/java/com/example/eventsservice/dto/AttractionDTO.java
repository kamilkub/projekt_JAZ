package com.example.eventsservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AttractionDTO {
    private Long id;
    private String name;
    private String url;
    private Set<Long> eventIds;
}
