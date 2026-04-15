package com.example.earthquake_assigment.services.interfaces;

import com.example.earthquake_assigment.domain.exceptions.DataBaseErrorWhileSaving;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.earthquake_assigment.domain.DTO.createEarthquakeDTO;
import com.example.earthquake_assigment.domain.model.Earthquake;

import java.time.Instant;
import java.util.List;

public interface EarthquakeService {

    String getJson();

    List<createEarthquakeDTO> mapEarthquakeDTO() throws JsonProcessingException;

    List<Earthquake> createEarthquakes(List<createEarthquakeDTO> dtos);

    List<Earthquake> filterByMagnitudeGreaterThanTwo();

    List<Earthquake> filterByDateAfter(Instant date);

    Earthquake deleteEarthquakeById(long id) throws DataBaseErrorWhileSaving;
}
