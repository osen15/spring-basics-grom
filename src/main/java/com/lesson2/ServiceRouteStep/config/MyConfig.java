package com.lesson2.ServiceRouteStep.config;

import com.lesson2.ServiceRouteStep.beans.Route;
import com.lesson2.ServiceRouteStep.beans.Service;
import com.lesson2.ServiceRouteStep.beans.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean public Route route(){
        return new Route();
    }
    @Bean public Service service(){
        return new Service();
    }
    @Bean public Step step(){
        return new Step();
    }


}
