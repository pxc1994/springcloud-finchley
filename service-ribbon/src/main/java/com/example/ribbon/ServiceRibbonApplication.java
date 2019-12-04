package com.example.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableHystrix //打开断路器
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "error")
    public String test(){
        RestTemplate restTemplate = restTemplate();
        return "ribbon : " + restTemplate.getForObject("http://hello-simona/hello",String.class);
    }

    public String error(){
        return "sorry , error !" ;
    }

}
