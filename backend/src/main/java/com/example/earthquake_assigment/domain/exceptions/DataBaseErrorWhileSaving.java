package com.example.earthquake_assigment.domain.exceptions;

public class DataBaseErrorWhileSaving extends RuntimeException {
    public DataBaseErrorWhileSaving() {
        super("Database error while saving earthquakes");
    }
}
