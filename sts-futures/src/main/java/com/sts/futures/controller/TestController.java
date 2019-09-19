package com.sts.futures.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangyi on 2019/9/19
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String testGetMethod(){
        return "this is futures application";
    }
}
