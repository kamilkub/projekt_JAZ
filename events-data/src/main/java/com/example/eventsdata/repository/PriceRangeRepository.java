package com.example.eventsdata.repository;

import com.example.eventsdata.entity.PriceRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRangeRepository extends JpaRepository<PriceRange, Long> {
}
