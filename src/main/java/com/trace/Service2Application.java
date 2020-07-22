package com.trace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Service2Application {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

    @RequestMapping("/test")
    public String test(){
        System.out.println("Service-2 called... from /test");
        return "Svc2 is responding ...";
    }
    
    @RequestMapping("/call2")
    public String call2(){
        System.out.println("Service-2 called...");

        return "Service-2 called... ";

    }

    @RequestMapping("/call3")
    public String call3() throws InterruptedException {
        System.out.println("Service-2 called...");

        Thread.sleep(3000);
        return "Service-2 called...  calling Service3 ---> \n"+
                restTemplate.getForObject("http://localhost:8773/call3", String.class);

    }
}
