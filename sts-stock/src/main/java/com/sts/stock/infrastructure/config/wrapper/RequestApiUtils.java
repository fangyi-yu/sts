package com.daoming.review.config.wrapper;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * Created by fangyi on 2019/9/14
 */
public class RequestApiUtils {

    /**
     * 获取请求的表单参数或url后缀参数
     * @param request HttpServletRequest
     * @return 表单参数或url后缀参数
     */
      static String fetchParam(HttpServletRequest request) {
         Enumeration<String> parameterNames = request.getParameterNames();
         if (parameterNames.hasMoreElements()){
            String paramName;
            String[] values;
             StringBuilder paramInfo = new StringBuilder();
            while (parameterNames.hasMoreElements()){
                paramName = (String)parameterNames.nextElement();
                values = request.getParameterValues(paramName);
                paramInfo.append("[").append(paramName).append(":").append(ArrayUtils.toString(values)).append("]");
            }
            return paramInfo.toString();
         }
         return "";
     }

    /**
     * 获取请求的头参数，注意 仅抓取以"custom_"开头的参数
     * @param request HttpServletRequest
     * @return 以"custom_"开头头信息
     */
      static String fetchHeadParams(HttpServletRequest request){
         Enumeration<String> headerNames = request.getHeaderNames();
         String headerName;
         String value;
         StringBuilder HeadParamInfo = null;
         while (headerNames.hasMoreElements()) {
             headerName = (String) headerNames.nextElement();
             if (headerName.contains("custom_")) {
                 HeadParamInfo =  null == HeadParamInfo ? new StringBuilder() : HeadParamInfo;
                 value = request.getHeader(headerName);
                 HeadParamInfo.append("[").append(headerName).append(":").append(ArrayUtils.toString(value)).append("]");
             }
         }
         return null == HeadParamInfo ? "" : HeadParamInfo.toString();
     }

    /**
     * aop获取请求体的json参数
     * @param request HttpServletRequest
     * @param joinPoint aop连接点
     * @return json参数
     */
     static String fetchJsonParams(HttpServletRequest request, JoinPoint joinPoint) {
        if (org.springframework.util.StringUtils.isEmpty(request.getContentType())) {
            return "";
        }
        if (!request.getContentType().contains("multipart/") && !request.getContentType().contains("application/json")) {
            return "";
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        int index = -1;
        for (int i = 0; i < method.getParameterAnnotations().length; i ++) {
            if (method.getParameterAnnotations()[i].length > 0) {
                for (int j = 0; j < method.getParameterAnnotations()[i].length; j ++) {
                    if (method.getParameterAnnotations()[i][j].annotationType() == RequestBody.class) {
                        index = i;
                        break;
                    }
                }
            }
        }
        if (index >= 0) {
            return joinPoint.getArgs()[index] instanceof String ? joinPoint.getArgs()[index].toString() : JSON.toJSONString(joinPoint.getArgs()[index]);
        }
        return "";
    }

    /**
     * aop获取请求的文件参数
     * @param request HttpServletRequest
     * @param joinPoint aop连接点
     * @return 文件参数
     */
     static String fetchFileParams(HttpServletRequest request, JoinPoint joinPoint){
         if (StringUtils.isEmpty(request.getContentType()) || !request.getContentType().contains("multipart/")){
             return "";
         }
        Object[] objs = joinPoint.getArgs();
         StringBuilder sb = null;
         if (objs != null && objs.length !=0 ){
             for (Object o : objs) {
                 if (o instanceof MultipartFile) {
                     MultipartFile mf = (MultipartFile) o;
                     sb =  sb == null ? new StringBuilder() : sb;
                     sb.append("[").append(mf.getName()).append(":").append(mf.getOriginalFilename()).append("]");
                 }else if (o instanceof MultipartFile[]) {
                     MultipartFile[] mfs = (MultipartFile[]) o;
                     sb =  sb == null ? new StringBuilder() : sb;
                     for (MultipartFile mf : mfs) {
                         sb.append("[").append(mf.getName()).append(":").append(mf.getOriginalFilename()).append("]");
                     }
                 }
             }
         }

        return sb == null ? "" : sb.toString();
    }


}
