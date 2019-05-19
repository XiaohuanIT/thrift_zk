package com.xiaohuan.server.controllers;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.rpc.weather_service.entity.Weather;
import com.xiaohuan.rpc.weather_service.request.GetCityWeatherRequest;
import com.xiaohuan.rpc.weather_service.response.GetCityWeatherResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohuan on 2019-04-28 23:03.
 */
public class ServiceImpl implements WeatherService.Iface {
    @Override
    public GetCityWeatherResponse get_city_weather(GetCityWeatherRequest request) {
        String city = request.getCity();
        GetCityWeatherResponse response = new GetCityWeatherResponse();
        response.setSuccess(true);
        Weather weather = new Weather();
        if(request.isSetCity()){
            weather.setCity("hello "+city);
            weather.setTemperature(20.1);
        }else {
            weather.setCity("hello, no city");
        }
        List<Weather> list = new ArrayList<>();
        list.add(weather);
        response.setWeather(list);
        return response;
    }
}
