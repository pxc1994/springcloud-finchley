package com.example.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author peng_xiaochun
 * @Date Created in 2019/12/3 10:23
 */
@FeignClient(value = "hello-simona",fallback = FeignHystric.class)
public interface FeignService {

    @GetMapping(value = "/hello")
    String hello();
}
