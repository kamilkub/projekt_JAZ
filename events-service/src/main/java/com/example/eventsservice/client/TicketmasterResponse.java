package com.example.eventsservice.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketmasterResponse {
    @JsonProperty("_embedded")
    private Embedded embedded;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded {
        private List<TicketmasterEvent> events;
    }
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class TicketmasterEvent {
    private String name;
    private String url;
    private Dates dates;

    @JsonProperty("_embedded")
    private Embedded embedded;

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
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attraction {
        private List<Classification> classifications;
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
}
