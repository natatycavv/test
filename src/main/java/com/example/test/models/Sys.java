package com.example.test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
    private String country;

    public String getCountry() {
        return country;
    }
}
