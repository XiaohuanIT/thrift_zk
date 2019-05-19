package com.xiaohuan;

import com.xiaohuan.client.WatchWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StartWeatherWithAPP implements ApplicationListener {
    @Autowired
    private WatchWeather watchWeather;

	@Override
    //@EventListener
	public void onApplicationEvent(ApplicationEvent arg0) {
        //WatchWeather watchWeather = new WatchWeather();
        try {
            watchWeather.watch();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
