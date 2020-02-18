package com.xiaohuan.thrift_demo_4;


import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.controllers.WeatherServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;

/**
 * @Author: xiaohuan
 * @Date: 2020/2/11 07:13
 */
public class Server {
	public static void main(String[] args) throws IOException, TTransportException {
		TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(8000);
		TProcessor tprocessor = new WeatherService.Processor<WeatherService.Iface>(new WeatherServiceImpl());

		// 半同步半异步
		THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
		thhsArgs.processor(tprocessor);
		thhsArgs.transportFactory(new TFramedTransport.Factory());
		thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

		TServer server = new THsHaServer(thhsArgs);
		System.out.println("Running HsHa Server");
		server.serve();






	}
}
