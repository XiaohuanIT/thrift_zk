namespace py weather_service.entity
namespace java com.xiaohuan.rpc.weather_service.entity

typedef string DateTime
typedef i32 int
typedef i64 long

struct Weather {
    1: required string city,
    2: optional string district,
    3: required double temperature
}