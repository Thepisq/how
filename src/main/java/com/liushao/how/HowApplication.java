package com.liushao.how;

import com.liushao.how.util.IdWorker;
import com.liushao.how.util.JwtUtil;

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

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
