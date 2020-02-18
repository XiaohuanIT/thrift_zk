package com.thrift_test;

import com.xiaohuan.rpc.weather_service.request.GetCityWeatherRequest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: xiaohuan
 * @Date: 2020/2/13 05:32
 */
public class DataTest {
	public static void main(String[] args) throws IOException, TException {
		//writeTBinaryProtocolData();
		//readTBinaryProtocolData();

		//writeTJSONProtocolData();
		readTJSONProtocolData();
	}

	private static void writeTBinaryProtocolData() throws IOException, TException {
		GetCityWeatherRequest request = new GetCityWeatherRequest();
		request.setCity("wuhan");
		request.setUser_id("001");
		FileOutputStream fos = new FileOutputStream(new File("request.txt"));
		request.write(new TBinaryProtocol(new TIOStreamTransport(fos)));
		fos.close();
	}

	private static void readTBinaryProtocolData() throws IOException, TException{
		GetCityWeatherRequest request = new GetCityWeatherRequest();
		FileInputStream fis = new FileInputStream(new File("request.txt"));
		request.read(new TBinaryProtocol(new TIOStreamTransport(fis)));
		System.out.println("city => " + request.getCity());
		System.out.println("user_id => " + request.getUser_id());
		fis.close();
	}


	private static void writeTJSONProtocolData() throws IOException, TException {
		GetCityWeatherRequest request = new GetCityWeatherRequest();
		request.setCity("wuhan");
		request.setUser_id("001");
		FileOutputStream fos = new FileOutputStream(new File("request.txt"));
		request.write(new TJSONProtocol(new TIOStreamTransport(fos)));
		fos.close();
	}

	private static void readTJSONProtocolData() throws IOException, TException{
		GetCityWeatherRequest request = new GetCityWeatherRequest();
		FileInputStream fis = new FileInputStream(new File("request.txt"));
		request.read(new TJSONProtocol(new TIOStreamTransport(fis)));
		System.out.println("city => " + request.getCity());
		System.out.println("user_id => " + request.getUser_id());
		fis.close();
	}
}
