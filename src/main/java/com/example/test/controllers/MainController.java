package com.example.test.controllers;

import com.example.test.models.Weather;
import com.example.test.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Weather> weathers = weatherRepository.findAll();
        Map<String, String> weatherData = new HashMap<>();

        for (var item: weathers) {
            weatherData.put(item.getCity(), item.getTemperature() > 0 ? "+" + Integer.toString((int)Math.round(item.getTemperature())) + "°C" : Integer.toString((int)Math.round(item.getTemperature())) + "°C");
        }

        model.addAttribute("title", "Weather");
        model.addAttribute("weatherData", weatherData);

        return "home";
    }
}