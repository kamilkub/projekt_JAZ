package com.example.eventsdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String date;
    private String ticketUrl;

    @ManyToOne(fetch = FetchType.LAZY) // lub FetchType.EAGER
    @JoinColumn(name = "venue_id") // Klucz obcy w tabeli `Event`
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private PriceRange priceRange;

    @ManyToMany
    @JoinTable(
            name = "event_attraction",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    private Set<Attraction> attractions;
}
