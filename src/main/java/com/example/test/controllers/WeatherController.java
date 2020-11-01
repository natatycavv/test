package com.example.test.controllers;

import com.example.test.models.Weather;
import com.example.test.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data")
public class WeatherController {
    @Autowired
    private WeatherRepository weatherRepository;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody
    List<Weather> getWeather(@RequestParam(value="name", required=false, defaultValue="Moscow") String name) {
        Iterable<Weather> weathers = weatherRepository.findAll();
        List<Weather> list = new ArrayList<>();
        weathers.forEach(list::add);
        return list;
    }

}