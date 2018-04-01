package com.farmer.micro.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Author farmer-coder
 * @Email aprimecoder@gmail.com
 * @Date Create at : 2018/3/13
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.farmer.micro"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
