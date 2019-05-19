include "weather/request.thrift"
include "weather/response.thrift"
include "weather/entity.thrift"

namespace py weather_service
namespace java com.xiaohuan.rpc.weather_service

service WeatherService {
    response.GetCityWeatherResponse get_city_weather(
        1: request.GetCityWeatherRequest request
    )
}