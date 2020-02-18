package com.xiaohuan.thrift_demo_5;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.rpc.weather_service.request.GetCityWeatherRequest;
import com.xiaohuan.rpc.weather_service.response.GetCityWeatherResponse;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @Author: xiaohuan
 * @Date: 2020/2/11 07:22
 */
public class Client {
	public static void main(String[] args) throws TException {
		for (int i = 0; i < 10; i++) {
			new Thread("Thread " + i) {
				@Override
				public void run() {
					// 设置传输通道 对于非阻塞服务 需要使用TFramedTransport(用于将数据分块发送)
					for (int j = 0; j < 10; j++) {
						TTransport transport = null;
						try {
							transport = new TFramedTransport(new TSocket("127.0.0.1", 8000, 10000));
							TProtocol protocol = new TBinaryProtocol(transport);
							WeatherService.Client client = new WeatherService.Client(protocol);
							transport.open();

							GetCityWeatherRequest request = new GetCityWeatherRequest();
							request.setCity("武汉");
							request.setUser_id("111");
							GetCityWeatherResponse response = client.get_city_weather(request);
							System.out.println("Response =: " + response);
							transport.close();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							// 关闭传输通道
							transport.close();
						}
					}
				}
			}.start();
		}


	}
}
