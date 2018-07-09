package com.sky.xljgps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class XljgpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XljgpsApplication.class, args);
    }
}
