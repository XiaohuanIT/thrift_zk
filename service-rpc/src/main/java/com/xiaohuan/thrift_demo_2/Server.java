package com.xiaohuan.thrift_demo_2;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.controllers.WeatherServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
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
		WeatherService.Processor<WeatherService.Iface> processor =
				new WeatherService.Processor<>(new WeatherServiceImpl());

		TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
		TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
		ttpsArgs.processor(processor);
		ttpsArgs.protocolFactory(protocolFactory);

		// 线程池服务模型 使用标准的阻塞式IO 预先创建一组线程处理请求
		TServer ttpsServer = new TThreadPoolServer(ttpsArgs);
		System.out.println("Running ThreadPool Server");
		ttpsServer.serve();
	}
}
