package com.example.earthquake_assigment.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.earthquake_assigment.domain.DTO.createEarthquakeDTO;
import com.example.earthquake_assigment.domain.model.Earthquake;
import org.springframework.web.bind.annotation.*;
import com.example.earthquake_assigment.services.interfaces.EarthquakeService;

import java.time.Instant;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/earthquake")
public class EarthquakeRestController {

    private final EarthquakeService earthquakeService;

    public EarthquakeRestController(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    @GetMapping("/fetch")
    public List<Earthquake> fetchEarthquakes() throws JsonProcessingException {

        List<createEarthquakeDTO> dtos = earthquakeService.mapEarthquakeDTO();
        return earthquakeService.createEarthquakes(dtos);
    }

    @GetMapping("/filterByMagnitude")
    public List<Earthquake> filterByMagnitude()  {
        return earthquakeService.filterByMagnitudeGreaterThanTwo();
    }

    @GetMapping("/filterByDate")
    public List<Earthquake> filterByDate(@RequestParam long date) {

        Instant time = Instant.ofEpochMilli(date);

        return earthquakeService.filterByDateAfter(time);
    }

    @DeleteMapping("/delete/{id}")
    public Earthquake deleteEarthquake(@PathVariable long id) {
        return earthquakeService.deleteEarthquakeById(id);
    }
}
