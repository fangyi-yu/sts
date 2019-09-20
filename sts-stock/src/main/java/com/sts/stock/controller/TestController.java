package com.sts.stock.controller;

import com.sts.stock.application.feign.FuturesTetsService;
import com.sts.stock.infrastructure.config.SinaFinance;
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

    @Autowired
    private SinaFinance sinaFinance;


    @GetMapping
    public String testGetMethod(){
        return "this is stock application";
    }

    @GetMapping("/stock-config")
    public SinaFinance testStockUrl(){
        return sinaFinance;
    }

    @GetMapping("/feign")
    public String testFeign(){
        return futuresTetsService.testGetMethod();
    }
}
