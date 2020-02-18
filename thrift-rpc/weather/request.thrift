include "entity.thrift"

namespace py weather_service.request
namespace java com.xiaohuan.rpc.weather_service.request

struct GetCityWeatherRequest {
    1: optional string city
    2: required string user_id
}