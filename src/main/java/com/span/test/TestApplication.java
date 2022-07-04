package com.span.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TestApplication {

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(TestApplication.class, args);
        log.info("APPLICATION FINISHED");

    }

}
