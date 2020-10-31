package com.example.test.controllers;

import com.example.test.models.Weather;
import com.example.test.models.WeatherReceive;
import com.example.test.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Weather> weathers = weatherRepository.findAll();
        model.addAttribute("title", "Weather");
        model.addAttribute("weathers", weathers);

        UpdateWeatherData();
        return "home";
    }

    public void UpdateWeatherData() {
        //var url = new URL("");
        RestTemplate restTemplate = new RestTemplate();
        var cities = new ArrayList<String>(Arrays.asList("Moscow", "Berlin", "London", "Washington"));
        for (var city: cities) {
            var data = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=23e0cf43ae6f9909fe970358dd9f45cf&units=metric", WeatherReceive.class);
            System.out.println("Data:    " + data);

            var weather = new Weather(data.getSys().getCountry(), data.getName(), data.getMain().getTemp(), new Date());
            weatherRepository.save(weather);
        }
    }
}