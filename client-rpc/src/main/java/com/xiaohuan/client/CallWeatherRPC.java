package com.xiaohuan.client;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.rpc.weather_service.entity.Weather;
import com.xiaohuan.rpc.weather_service.request.GetCityWeatherRequest;
import com.xiaohuan.rpc.weather_service.response.GetCityWeatherResponse;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import static com.xiaohuan.client.constants.WeatherConstants.RPCNAME;

@Component
public class CallWeatherRPC {
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


	/**
	 *
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public String callWeather(String city) throws Exception {
		String ip="";
		Integer port= null;
		Map<String, Integer> hostMap = this.getServiceHost();
		int randomIndex = new Random().nextInt(hostMap.size());
		int index = 0;
		for(Map.Entry<String, Integer> entry : hostMap.entrySet()){
			if(index++ == randomIndex){
				ip = entry.getKey();
				port = entry.getValue();
				break;
			}
		}
		return this.callWeather(ip, port, city);
	}


	public Map<String, Integer> getServiceHost() throws Exception {
		Map<String, Integer> map = new HashMap<>();

		// 1.Connect to zk
		CuratorFramework client = CuratorFrameworkFactory.newClient(
				zkConnectionHosts,
				new RetryNTimes(10, 5000)
		);
		client.start();
		Stat stat = client.checkExists().forPath(RPCNAME);
		if(stat != null) {
			Object result = client.getChildren().forPath(RPCNAME);
			for(String hosts : (ArrayList<String>)result){
				int colonPosition = hosts.indexOf(":");
				map.put(hosts.substring(0, colonPosition), Integer.parseInt(hosts.substring(colonPosition+1)));
			}
		}
		return map;
	}
}
