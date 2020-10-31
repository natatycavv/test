package com.example.test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainParams {
    private double temp;

    public double getTemp() {
        return temp;
    }
}
