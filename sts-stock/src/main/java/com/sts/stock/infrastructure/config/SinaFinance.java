package com.sts.stock.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fangyi on 2019/9/12
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "sinafinance")
public class SinaFinance {

    // 一次最多获取多少个公司的股票信息
    private int maxNumber;

    //获取股票信息的地址
    private String stockUrl;

    //获取股票信息的公司代码前缀
    private List<String> stockCodePrefixs;
}
