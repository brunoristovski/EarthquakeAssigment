package com.example.earthquake_assigment.services.implementations;

import com.example.earthquake_assigment.domain.model.Earthquake;
import com.example.earthquake_assigment.repository.EarthquakeRepository;
import com.example.earthquake_assigment.services.interfaces.EarthquakeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class EarthquakeServiceIntegrationTest {

    @Autowired
    private EarthquakeService earthquakeService;

    @Autowired
    private EarthquakeRepository repository;

    @Test
    void testSaveAndDeleteEarthquake() {

        Earthquake eq = new Earthquake();
        eq.setMagnitude(4.2);
        eq.setPlace("Skopje");
        eq.setDate(Instant.now());

        Earthquake saved = repository.save(eq);

        assertNotNull(saved.getId());

        earthquakeService.deleteEarthquakeById(saved.getId());

        assertTrue(repository.findById(saved.getId()).isEmpty());
    }

    @Test
    void testFilterByMagnitude() {

        Earthquake eq1 = new Earthquake();
        eq1.setMagnitude(1.5);
        eq1.setPlace("Ohrid");
        eq1.setDate(Instant.now());

        Earthquake eq2 = new Earthquake();
        eq2.setMagnitude(5.0);
        eq2.setPlace("Skopje");
        eq2.setDate(Instant.now());

        repository.save(eq1);
        repository.save(eq2);

        var result = earthquakeService.filterByMagnitudeGreaterThanTwo();

        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(e -> e.getMagnitude() > 2));
    }

    @Test
    void testFilterByDate() {

        Instant now = Instant.now();

        Earthquake eq = new Earthquake();
        eq.setMagnitude(3.0);
        eq.setPlace("Bitola");
        eq.setDate(now.plusSeconds(10));

        repository.save(eq);

        var result = earthquakeService.filterByDateAfter(now);

        assertEquals(1, result.size());
    }
}