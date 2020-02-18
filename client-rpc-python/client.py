import sys

from weather_service.WeatherService import Client
from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.protocol import TJSONProtocol
from weather_service.request.ttypes import GetCityWeatherRequest


def to_server():
    transport = TSocket.TSocket('127.0.0.1', 8000)
    transport = TTransport.TBufferedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = Client(protocol)
    # Connect!
    transport.open()



    request = GetCityWeatherRequest()
    request.city = "wuhan"
    request.user_id="001"

    response = client.get_city_weather(request)
    print response
    transport.close()


def serialize_TBinaryProtocol():
    request = GetCityWeatherRequest()
    request.city = "wuhan"
    request.user_id="001"
    tmembuf = TTransport.TMemoryBuffer()
    tbinprot = TBinaryProtocol.TBinaryProtocol(tmembuf)
    request.write(tbinprot)
    print tmembuf.getvalue()
    return tmembuf.getvalue()

def deserialize_TBinaryProtocol(serialize_val):
    tmembuf = TTransport.TMemoryBuffer(serialize_val)
    tbinprot = TBinaryProtocol.TBinaryProtocol(tmembuf)

    request = GetCityWeatherRequest()
    request.read(tbinprot)
    print request, request.city, request.user_id


def serialize_TJSONProtocol():
    request = GetCityWeatherRequest()
    request.city = "wuhan"
    #request.user_id="001"

    tmembuf = TTransport.TMemoryBuffer()
    tbinprot = TJSONProtocol.TJSONProtocol(tmembuf)
    request.write(tbinprot)
    print tmembuf.getvalue()
    return tmembuf.getvalue()

def deserialize_TJSONProtocol(serialize_val):
    tmembuf = TTransport.TMemoryBuffer(serialize_val)
    tbinprot = TJSONProtocol.TJSONProtocol(tmembuf)

    request = GetCityWeatherRequest()
    request.read(tbinprot)
    print request, request.city, request.user_id




if __name__ == '__main__':
    #binary_result = serialize_TBinaryProtocol()
    #deserialize_TBinaryProtocol(binary_result)

    json_result = serialize_TJSONProtocol()
    #json_result = {"1":{"str":"wuhan"}}
    deserialize_TJSONProtocol(json_result)
