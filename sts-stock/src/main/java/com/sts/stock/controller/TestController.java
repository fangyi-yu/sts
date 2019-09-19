package com.sts.stock.controller;

import com.sts.stock.application.feign.FuturesTetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangyi on 2019/9/19
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FuturesTetsService futuresTetsService;

    @GetMapping
    public String testGetMethod(){
        return "this is stock application";
    }

    @GetMapping("/feign")
    public String testFeign(){
        return futuresTetsService.testGetMethod();
    }
}
