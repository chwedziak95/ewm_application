package com.kc6379.zarzadaniemagazynem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class ZarzadanieMagazynemApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZarzadanieMagazynemApplication.class, args);
    }

}
