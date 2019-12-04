package com.example.feign;

import com.example.feign.service.FeignService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }

    @Resource
    private FeignService feignService;


    @GetMapping(value = "/hi")
    public String hi (){
        return "feign : " + feignService.hello();
    }
}
