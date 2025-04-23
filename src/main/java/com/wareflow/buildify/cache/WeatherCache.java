package com.wareflow.buildify.cache;

import com.wareflow.buildify.dto.WeatherInfoDTO;

import java.util.List;

public class WeatherCache {

    private static final WeatherCache instance = new WeatherCache();
    private List<WeatherInfoDTO> weatherInfoDTOList;

    private WeatherCache() {
    }

    public static WeatherCache getInstance() {
        if (instance == null) {
            throw new IllegalStateException("List is not initialized. Call getInstance(List<InboundStatsDTO>) first.");
        }
        return instance;
    }

    public List<WeatherInfoDTO> getWeatherInfoCache() {
        return weatherInfoDTOList;
    }

    public void setWeatherInfoCache(List<WeatherInfoDTO> weatherInfoDTOList) {
        this.weatherInfoDTOList = weatherInfoDTOList;
    }
}
