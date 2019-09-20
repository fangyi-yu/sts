package com.sts.stock.infrastructure.config.wrapper;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fangyi on 2019/9/20
 */
public class IpUtils {

    private static String getClientIP(HttpServletRequest request) {
        return request.getHeader("x-forwarded-for") == null?request.getRemoteAddr():request.getHeader("x-forwarded-for");
    }

     static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
    }
}
