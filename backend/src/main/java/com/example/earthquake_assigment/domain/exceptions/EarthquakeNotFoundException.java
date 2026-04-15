package com.example.earthquake_assigment.domain.exceptions;

public class EarthquakeNotFoundException extends RuntimeException {
    public EarthquakeNotFoundException() {
        super("Earthquake not found by ID");
    }
}
