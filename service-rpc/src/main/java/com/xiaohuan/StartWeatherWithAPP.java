package com.xiaohuan;

import java.io.IOException;
import com.xiaohuan.server.RegisterWeatherRPC;
import com.xiaohuan.server.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartWeatherWithAPP implements ApplicationListener {
    @Autowired
    private RegisterWeatherRPC registerWeatherRPC;

    //@EventListener
    @Override
	public void onApplicationEvent(ApplicationEvent arg0) {
        startService();
        registerWeatherRPC();
	}

    private void registerWeatherRPC() {
	    System.out.println("start registerWeatherRPC...");
        //RegisterWeatherRPC registerWeatherRPC = new RegisterWeatherRPC();
        try {
            registerWeatherRPC.register();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void startService(){
        Thread thread = new Thread(new StartService());
        thread.start();
    }
}
