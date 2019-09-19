package com.sts.stock.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fangyi on 2019/9/19
 */
@FeignClient(name = "sts-futures")
public interface FuturesTetsService {

    @GetMapping("/test")
    String testGetMethod();
}
