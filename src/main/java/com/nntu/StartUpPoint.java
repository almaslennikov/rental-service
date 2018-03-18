package com.nntu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class StartUpPoint {

    public static void main(String[] args) {
        SpringApplication.run(StartUpPoint.class, args);
    }
}
