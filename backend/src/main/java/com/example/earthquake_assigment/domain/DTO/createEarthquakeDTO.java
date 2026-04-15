package com.example.earthquake_assigment.domain.DTO;

public record createEarthquakeDTO(

        double mag,

        String magType,

        String place,

        String title,

        long time,

        double longitude,

        double latitude
        ){}
