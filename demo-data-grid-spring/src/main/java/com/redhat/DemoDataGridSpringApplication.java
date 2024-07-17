package com.redhat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DemoDataGridSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoDataGridSpringApplication.class, args);
    }

}
