package com.example.earthquake_assigment.domain.exceptions;

public class MissingFeaturesInJsonException extends RuntimeException {
    public MissingFeaturesInJsonException() {
        super("Missing 'features' in JSON response");
    }
}
