package com.xiaohuan.thrift_demo_1;


import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.controllers.WeatherServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @Author: xiaohuan
 * @Date: 2020/2/11 07:13
 */
public class Server {
	public static void main(String[] args) throws IOException, TTransportException {
		ServerSocket serverSocket = new ServerSocket(8000);
		TServerSocket serverTransport = new TServerSocket(serverSocket);
		WeatherService.Processor processor =
				new WeatherService.Processor<WeatherService.Iface>(new WeatherServiceImpl());
		TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();

		TSimpleServer.Args tArgs = new TSimpleServer.Args(serverTransport);
		tArgs.processor(processor);
		tArgs.protocolFactory(protocolFactory);
		// 简单的单线程服务模型 一般用于测试
		TServer tServer = new TSimpleServer(tArgs);
		System.out.println("Running Simple Server");
		tServer.serve();
	}
}
