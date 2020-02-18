package com.xiaohuan.server;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.constants.WeatherConstants;
import com.xiaohuan.server.controllers.WeatherServiceImpl;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

/**
 * Created by xiaohuan on 2019-05-12 21:38.
 */

public class ServerTest {
    private static boolean breg = false;

    public void method_1() throws TTransportException {
        //TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
        TJSONProtocol.Factory proFactory = new TJSONProtocol.Factory();

        WeatherServiceImpl handler = new WeatherServiceImpl();
        WeatherService.Processor processor= new WeatherService.Processor(handler);
        TServerTransport serverTransport = new TServerSocket(WeatherConstants.WeahterPort);

        TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
        TTransportFactory transportFactory= new TTransportFactory();
        serverArgs.transportFactory(transportFactory);
        serverArgs.processor(processor);
        serverArgs.protocolFactory(proFactory);
        //serverArgs.minWorkerThreads(1);
        //serverArgs.maxWorkerThreads(1);
        TServer server = new TThreadPoolServer(serverArgs);
        server.serve();
    }


    public void method_2() throws TTransportException {
        WeatherServiceImpl handler = new WeatherServiceImpl();
        WeatherService.Processor<WeatherService.Iface> tprocessor= new WeatherService.Processor<WeatherService.Iface>(handler);
        //简单的单线程服务模型，一般用于测试
        TServerSocket serverTransport = new TServerSocket(WeatherConstants.WeahterPort);
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        // tArgs.protocolFactory(new TBinaryProtocol.Factory());
        //tArgs.protocolFactory(new TCompactProtocol.Factory());
        tArgs.protocolFactory(new TJSONProtocol.Factory());
        TServer server = new TSimpleServer(tArgs);
        server.serve();
    }

    public static void main(String[] args) throws TTransportException {
        ServerTest server = new ServerTest();
        server.method_1();
        //server.method_2();
    }
}
