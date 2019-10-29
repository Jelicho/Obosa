package com.ssafy.obosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableScheduling
public class ObosaApplication {

    public static void main(String[] args)
    {
            SpringApplication.run(ObosaApplication.class, args);
    }

}
