package com.postgre.springapipostgre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.postgre.springapipostgre")
public class SpringApiPostgreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiPostgreApplication.class, args);
    }

}
