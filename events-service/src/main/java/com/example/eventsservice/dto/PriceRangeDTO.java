package com.example.eventsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRangeDTO {
    private Long id;
    private String currency;
    private Double minPrice;
    private Double maxPrice;

}
