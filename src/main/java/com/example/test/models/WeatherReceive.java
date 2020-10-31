package com.example.test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherReceive {
    private MainParams main;
    private long id;
    private String name;
    private Sys sys;

    public long getId() {
        return id;
    }

    public MainParams getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Sys getSys() {
        return sys;
    }
}

