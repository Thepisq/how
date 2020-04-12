package com.liushao.how;

import com.liushao.how.util.IdWorker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HowApplication {

    public static void main(String[] args) {
        SpringApplication.run(HowApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
