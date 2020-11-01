package com.example.test;

import com.example.test.models.Weather;
import com.example.test.models.WeatherReceive;
import com.example.test.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
public class StartUpApp {
    @Value("${custom.config.variable}")
    private int x;

    @Autowired
    private WeatherRepository weatherRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() throws InterruptedException {
        while (true) {
            LoadWeatherData();
            Thread.sleep(60000 * x);
        }
    }

    public void LoadWeatherData() {
        RestTemplate restTemplate = new RestTemplate();
        var cities = new ArrayList<String>(Arrays.asList("Moscow", "Berlin", "London", "Washington"));

        for (var city: cities) {
            var data = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=23e0cf43ae6f9909fe970358dd9f45cf&units=metric", WeatherReceive.class);

            var weather = new Weather(data.getSys().getCountry(), data.getName(), data.getMain().getTemp(), new Date());
            weatherRepository.save(weather);
        }
    }
}