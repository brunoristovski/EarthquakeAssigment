package com.example.earthquake_assigment.services.implementations;

import com.example.earthquake_assigment.domain.exceptions.DataBaseErrorWhileSaving;
import com.example.earthquake_assigment.domain.exceptions.EarthquakeNotFoundException;
import com.example.earthquake_assigment.domain.exceptions.MissingFeaturesInJsonException;
import com.example.earthquake_assigment.domain.exceptions.UrlConnectionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.earthquake_assigment.domain.DTO.createEarthquakeDTO;
import com.example.earthquake_assigment.domain.model.Earthquake;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.example.earthquake_assigment.repository.EarthquakeRepository;
import com.example.earthquake_assigment.services.interfaces.EarthquakeService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final EarthquakeRepository earthquakeRepository;

    public EarthquakeServiceImpl(EarthquakeRepository earthquakeRepository) {
        this.earthquakeRepository = earthquakeRepository;
    }

    @Transactional
    @Override
    public String getJson() {
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
        try{
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e)
            {
                throw new UrlConnectionException();
            }
    }

    @Transactional
    @Override
    public List<createEarthquakeDTO> mapEarthquakeDTO() throws JsonProcessingException {

        String json = getJson();

        JsonNode root = mapper.readTree(json);

        if (root == null || !root.has("features")) {
            throw new MissingFeaturesInJsonException();
        }

        JsonNode features = root.get("features");

        List<createEarthquakeDTO> list = new ArrayList<>();

        for (JsonNode feature : features) {

            if (!feature.has("properties") || !feature.has("geometry")) {
                continue;
            }

            JsonNode props = feature.get("properties");
            JsonNode geometry = feature.get("geometry");

            if (props == null || geometry == null) continue;

            JsonNode magNode = props.get("mag");
            JsonNode magTypeNode = props.get("magType");
            JsonNode placeNode = props.get("place");
            JsonNode titleNode = props.get("title");
            JsonNode timeNode = props.get("time");

            JsonNode coords = geometry.get("coordinates");

            if (magNode == null || timeNode == null || coords == null || coords.size() < 2) {
                continue;
            }

            double mag = magNode.asDouble();
            String magType = magTypeNode != null ? magTypeNode.asText() : "unknown";
            String place = placeNode != null ? placeNode.asText() : "unknown";
            String title = titleNode != null ? titleNode.asText() : "unknown";
            long time = timeNode.asLong();

            double longitude = coords.get(0).asDouble();
            double latitude = coords.get(1).asDouble();

            list.add(new createEarthquakeDTO(
                    mag,
                    magType,
                    place,
                    title,
                    time,
                    longitude,
                    latitude
            ));
        }

        return list;
    }

    @Transactional
    @Override
    public List<Earthquake> createEarthquakes(List<createEarthquakeDTO> dtos) {

        List<Earthquake> entities = new ArrayList<>();

        for (createEarthquakeDTO dto : dtos) {

            Earthquake earthquake = new Earthquake();
            earthquake.setMagnitude(dto.mag());
            earthquake.setMagType(dto.magType());
            earthquake.setPlace(dto.place());
            earthquake.setTitle(dto.title());

            Instant dateTime = Instant.ofEpochMilli(dto.time());

            earthquake.setDate(dateTime);
            earthquake.setLatitude(dto.latitude());
            earthquake.setLongitude(dto.longitude());

            entities.add(earthquake);
        }

        try {
            earthquakeRepository.deleteAll();
            return earthquakeRepository.saveAll(entities);
        } catch (Exception e) {
            throw new DataBaseErrorWhileSaving();
        }
    }

    @Transactional
    @Override
    public List<Earthquake> filterByMagnitudeGreaterThanTwo() {
        return earthquakeRepository.findEarthquakeByMagnitudeGreaterThan(2.0);
    }

    @Transactional
    @Override
    public List<Earthquake> filterByDateAfter(Instant date) {
        return earthquakeRepository.findAllAfter(date);
    }

    @Transactional
    @Override
    public Earthquake deleteEarthquakeById(long id) {
        Earthquake earthquake = earthquakeRepository.findById(id).orElseThrow(EarthquakeNotFoundException::new);
        earthquakeRepository.delete(earthquake);
        return earthquake;
    }
}
