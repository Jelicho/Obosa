package com.ssafy.obosa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ObosaApplication {

    public static void main(String[] args)
    {
            SpringApplication.run(ObosaApplication.class, args);
    }

}
