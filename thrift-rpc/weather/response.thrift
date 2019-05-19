include "entity.thrift"

namespace py weather_service.response
namespace java com.xiaohuan.rpc.weather_service.response

typedef i32 int

struct GetCityWeatherResponse {
    1: required bool success,
    2: optional string error_message,
    3: optional list<entity.Weather> weather
}