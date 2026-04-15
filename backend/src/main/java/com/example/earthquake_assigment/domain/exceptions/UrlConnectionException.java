package com.example.earthquake_assigment.domain.exceptions;

public class UrlConnectionException extends RuntimeException {
    public UrlConnectionException() {
        super("USGS API is not available");
    }
}
