package com.example.eventsservice.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketmasterEvent {
    private String name;
    private String url;
    private Dates dates;

    @JsonProperty("_embedded")
    private Embedded embedded;

    private List<PriceRange> priceRanges;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dates {
        private Start start;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Start {
            private String localDate;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded {
        private List<Venue> venues;
        private List<Attraction> attractions;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Venue {
        private String name;
        private City city;
        private Country country;
        private Address address;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class City {
            private String name;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Country {
            private String name;
            private String countryCode;
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Address {
            private String line1;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attraction {
        private List<Classification> classifications;
        private String name;
        private String url;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Classification {
        private Genre genre;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Genre {
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PriceRange {
        private String currency;
        private Long min;
        private Long max;
    }

}
