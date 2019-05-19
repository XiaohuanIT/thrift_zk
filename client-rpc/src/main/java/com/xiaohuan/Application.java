package com.xiaohuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiaohuan on 2019-04-30 21:21.
 */
@SpringBootApplication
public class Application{
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        //application.addListeners(new StartWeatherWithAPP());
        application.run(args);
    }



}
