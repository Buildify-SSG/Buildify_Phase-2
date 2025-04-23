package com.wareflow.buildify.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.wareflow.buildify.dto.WeatherInfoDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

//@Service
@Component
public class WeatherUtil {
    private static final List<String> CITIES = List.of("Seoul", "Seongnam", "Busan", "Daegu", "Incheon");
    private final String apiKey;
    private final RestTemplate restTemplate;

//    @Autowired
    public WeatherUtil(@Value("${openweather.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
    }

    public List<WeatherInfoDTO> fetchAllCityWeather() {
        return CITIES.stream()
                .map(this::fetchWeatherForCity)
                .collect(Collectors.toList());
    }

    private WeatherInfoDTO fetchWeatherForCity(String city) {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=kr",
                city, apiKey);
        try {
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);
            String desc = root.at("/weather/0/description").asText("정보없음");
            double temp = root.at("/main/temp").asDouble(Double.NaN);
            String emoji = mapWeatherToEmoji(desc);
            return new WeatherInfoDTO(city, emoji, desc, temp);
        } catch (Exception e) {
            String emoji = mapWeatherToEmoji("불러오기 실패");
            return new WeatherInfoDTO(city, emoji, "불러오기 실패", Double.NaN);
        }
    }


    private String mapWeatherToEmoji(String desc) {
        if (desc.contains("맑음")) return "☀️";
        if (desc.contains("구름")) return "☁️";
        if (desc.contains("비")) return "🌧️";
        if (desc.contains("눈")) return "❄️";
        if (desc.contains("흐림")) return "🌫️";
        return "🌡️";
    }
}