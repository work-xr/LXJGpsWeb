package com.sky.xljgps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * description:
 * author:  hefeng
 * create:  2018-07-11  下午1:35
 */

@SpringBootApplication(scanBasePackages = "com.sky.xljgps")
//@ComponentScan("com.sky.xljgps.controller")
public class XljgpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XljgpsApplication.class, args);
    }
}
