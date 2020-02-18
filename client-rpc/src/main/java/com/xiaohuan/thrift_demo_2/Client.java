package com.xiaohuan.thrift_demo_2;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.rpc.weather_service.request.GetCityWeatherRequest;
import com.xiaohuan.rpc.weather_service.response.GetCityWeatherResponse;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @Author: xiaohuan
 * @Date: 2020/2/11 07:22
 */
public class Client {
	public static void main(String[] args) throws TException {
		TTransport transport = new TSocket("127.0.0.1", 8000, 10000);
		TProtocol protocol = new TBinaryProtocol(transport);
		WeatherService.Client client = new WeatherService.Client(protocol);
		transport.open();

		GetCityWeatherRequest request = new GetCityWeatherRequest();
		request.setCity("武汉");
		GetCityWeatherResponse response = client.get_city_weather(request);

		System.out.println("Response =: " + response);
		transport.close();
	}
}
