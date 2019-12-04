package com.example.feign.service;


import org.springframework.stereotype.Component;

@Component
public class FeignHystric implements FeignService{

    @Override
    public String hello() {
        return "sorry , error ";
    }
}
