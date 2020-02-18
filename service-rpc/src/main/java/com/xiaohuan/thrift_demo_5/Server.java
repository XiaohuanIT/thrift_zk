package com.xiaohuan.thrift_demo_5;


import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.controllers.WeatherServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
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
		TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8000);
		TProcessor processor = new WeatherService.Processor<WeatherService.Iface>(new WeatherServiceImpl());
		// 多线程半同步半异步
		TThreadedSelectorServer.Args ttssArgs = new TThreadedSelectorServer.Args(serverSocket);
		ttssArgs.processor(processor);
		ttssArgs.protocolFactory(new TBinaryProtocol.Factory());
		// 使用非阻塞式IO时 服务端和客户端都需要指定数据传输方式为TFramedTransport
		ttssArgs.transportFactory(new TFramedTransport.Factory());

		// 多线程半同步半异步的服务模型
		TThreadedSelectorServer server = new TThreadedSelectorServer(ttssArgs);
		System.out.println("Running ThreadedSelector Server");
		server.serve();
	}
}
