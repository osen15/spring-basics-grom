package com.lesson3.dependencyInversionExample;

import org.springframework.context.annotation.Bean;


public interface DbConnector {


    void connect();


    void save();


    void disconnect();


}
