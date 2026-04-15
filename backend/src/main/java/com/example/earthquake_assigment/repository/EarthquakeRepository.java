package com.example.earthquake_assigment.repository;

import com.example.earthquake_assigment.domain.model.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface EarthquakeRepository extends JpaRepository<Earthquake, Long> {

    List<Earthquake> findEarthquakeByMagnitudeGreaterThan(double magnitudeIsGreaterThan);

    @Query("SELECT e FROM Earthquake e WHERE e.date > :date")
    List<Earthquake> findAllAfter(@Param("date") Instant date);

    @Override
    void deleteAll();
}
