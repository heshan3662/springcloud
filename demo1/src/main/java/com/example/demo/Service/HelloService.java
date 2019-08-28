package com.example.demo.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Value("${server.port}")
    String port;
    public String hiService(String name) {
        return "hi," + name  +port;
    }


    @HystrixCommand(fallbackMethod = "hiError")
    public String hiError(String name) {
        return "hi," + name + ",sorry,error!"+port;
    }
}