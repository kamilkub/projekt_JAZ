package com.example.eventsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRangeDTO {
    private String currency;
    private Long minPrice;
    private Long maxPrice;
}
