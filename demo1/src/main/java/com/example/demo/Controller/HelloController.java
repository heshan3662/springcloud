package com.example.demo.Controller;

import com.example.demo.jdbctemplate.DBTools;
import com.example.demo.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class HelloController {
    @Autowired
    HelloService helloService;
    @Autowired
    DBTools DBTools;
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hiService( String name) {
        return  helloService.hiService(name)  ;
    }



 }