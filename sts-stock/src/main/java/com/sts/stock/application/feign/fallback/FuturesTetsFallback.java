package com.sts.stock.application.feign.fallback;

/**
 * Created by fangyi on 2019/9/20
 */

import com.sts.stock.application.feign.FuturesTetsService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FuturesTetsFallback implements FuturesTetsService {


    @Override
    public String testGetMethod() {
        log.error("testGetMethod error");
        return "请求失败";
    }
}
