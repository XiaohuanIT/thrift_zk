package com.xiaohuan.server;

import com.xiaohuan.rpc.weather_service.WeatherService;
import com.xiaohuan.server.constants.WeatherConstants;
import com.xiaohuan.server.controllers.ServiceImpl;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

/**
 * Created by xiaohuan on 2019-05-01 17:04.
 */
public class StartService implements Runnable{
    private static boolean breg = false;

    public void starWeather() throws TTransportException {
        //TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
        TJSONProtocol.Factory proFactory = new TJSONProtocol.Factory();

        ServiceImpl handler = new ServiceImpl();
        WeatherService.Processor processor= new WeatherService.Processor(handler);
        TServerTransport serverTransport = new TServerSocket(WeatherConstants.WeahterPort);

        TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
        TTransportFactory transportFactory= new TTransportFactory();
        serverArgs.transportFactory(transportFactory);
        serverArgs.processor(processor);
        serverArgs.protocolFactory(proFactory);
        serverArgs.minWorkerThreads(1);
        serverArgs.maxWorkerThreads(1);
        TServer server = new TThreadPoolServer(serverArgs);
        breg = true;
        server.serve();
    }


    @Override
    public void run() {
        try {
            if (!breg)
                starWeather();
        } catch (TTransportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
