package com.xiaohuan.thrift_demo_3;


import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.controllers.WeatherServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
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
		TProcessor tprocessor = new WeatherService.Processor<WeatherService.Iface>(new WeatherServiceImpl());
		TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(8000);

		TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
		tnbArgs.processor(tprocessor);
		tnbArgs.transportFactory(new TFramedTransport.Factory());
		tnbArgs.protocolFactory(new TCompactProtocol.Factory());

		// 使用非阻塞式IO服务端和客户端需要指定TFramedTransport数据传输的方式
		TServer server = new TNonblockingServer(tnbArgs);
		System.out.println("Running Non-blocking Server");
		server.serve();
	}
}
