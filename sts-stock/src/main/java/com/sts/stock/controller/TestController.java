package com.sts.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fangyi on 2019/9/19
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping
    public String testGetMethod(){
        return "this is stock service";
    }
}
