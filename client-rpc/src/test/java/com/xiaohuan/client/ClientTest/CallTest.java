package com.xiaohuan.client.ClientTest;

import com.xiaohuan.client.constants.WeatherConstants;
import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.rpc.weather_service.entity.Weather;
import com.xiaohuan.rpc.weather_service.request.GetCityWeatherRequest;
import com.xiaohuan.rpc.weather_service.response.GetCityWeatherResponse;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CallTest {
	@Value("${zk.connection.hosts}")
	private String zkConnectionHosts;
	@Value("${zk.connection.timeOut}")
	private int zkConnectionTimeOut;

	public String callWeather(String ip, int port, String city) {
		String retString = null;
		try {
			TTransport transport = new TSocket(ip, port);
			transport.open();
			//TProtocol protocol = new TBinaryProtocol(transport);
			TProtocol protocol = new TJSONProtocol(transport);
			WeatherService.Client client = new WeatherService.Client(protocol);
			GetCityWeatherRequest request = new GetCityWeatherRequest();
			request.setCity(city);

			GetCityWeatherResponse response = client.get_city_weather(request);
			if(response.isSuccess()){
				List<Weather> weatherList = response.getWeather();
				System.out.println(weatherList.get(0).getCity());
				retString = weatherList.get(0).getCity() + weatherList.get(0).getTemperature();
			}else{
				System.out.println(response.getError_message());
			}
			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		System.out.println("retString:"+retString);
		return retString;
	}

	public void testTest(String SERVER_IP, int SERVER_PORT, int TIMEOUT){
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
		// 协议要和服务端一致
		// TProtocol protocol = new TBinaryProtocol(transport);
		//TProtocol protocol = new TCompactProtocol(transport);
		TProtocol protocol = new TJSONProtocol(transport);
		WeatherService.Client client = new WeatherService.Client(protocol);
		transport.open();
		String result=null;
			GetCityWeatherRequest request = new GetCityWeatherRequest();
			request.setCity("123456");

			GetCityWeatherResponse response = client.get_city_weather(request);
			if(response.isSuccess()){
				List<Weather> weatherList = response.getWeather();
				System.out.println(weatherList.get(0).getCity());
				result = weatherList.get(0).getCity() + weatherList.get(0).getTemperature();
			}else{
				System.out.println(response.getError_message());
			}
		System.out.println("Thrift client result =: " + result);
	} catch (TTransportException e) {
		e.printStackTrace();
	} catch (TException e) {
		e.printStackTrace();
	} finally {
		if (null != transport) {
			transport.close();
		}
	}
	}

	public static void main(String[] args) {
		CallTest client = new CallTest();
		//client.testTest("127.0.0.1", WeatherConstants.WeahterPort, 100000);
		client.callWeather("127.0.0.1", WeatherConstants.WeahterPort, "123");
	}


}
