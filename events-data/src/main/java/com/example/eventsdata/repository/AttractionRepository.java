package com.example.eventsdata.repository;

import com.example.eventsdata.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Attraction findByName(String name);
}
