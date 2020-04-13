package com.liushao.how;

import com.liushao.how.util.IdWorker;
import com.liushao.how.util.JwtUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    /**
     * 使用时需要启动时注入
     * @return BCryptPasswordEncoder
     */
    @Bean
	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
    }
}
