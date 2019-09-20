package com.sts.stock.infrastructure.config.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by fangyi on 2019/9/20
 */
@Slf4j
@Aspect
@Component
public class RequestApiAspect {

    private static final boolean logEnabled = log.isInfoEnabled();


//    @Pointcut("execution( * com..controller..*.*(..))")
    @Pointcut("execution(* com.sts.*.controller..*.*(..))")
    public void webLog(){}


/*
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){

        // 接收到请求，记录请求内容

        logger.info("WebLogAspect.doBefore()");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容

        logger.info("URL : " + request.getRequestURL().toString());

        logger.info("HTTP_METHOD : " + request.getMethod());

        logger.info("IP : " + request.getRemoteAddr());

        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //获取所有参数方法一：

        Enumeration<String> enu=request.getParameterNames();

        while(enu.hasMoreElements()){

            String paraName=(String)enu.nextElement();

            System.out.println(paraName+": "+request.getParameter(paraName));

        }

    }
    */


    @Before(value = "webLog()")
    public void beforeRequet(JoinPoint pjp){
        if (!logEnabled) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String apiOperationValue = RequestApiUtils.fetchApiOperationValue(pjp);
        String params = RequestApiUtils.fetchParam(request);
        String headParams = RequestApiUtils.fetchHeadParams(request);
        String jsonParams = RequestApiUtils.fetchJsonParams(request, pjp);
        String fileParams = RequestApiUtils.fetchFileParams(request, pjp);
        log.info("request [{}] => [{}] [{}] {} {}",
                IpUtils.getIpAddress(request),
//                apiOperationValue,
                request.getMethod(),
                request.getRequestURL(),
                params + fileParams + headParams,
                jsonParams);
        request.setAttribute("_START_TIME", System.currentTimeMillis());
    }

    @After(value = "webLog()")
    public void afterRequest() throws UnsupportedEncodingException {
        if (!logEnabled) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request.getRequestURI().contains("/error")) {
            log.info("response [{}] =>[{}] ", IpUtils.getIpAddress(request), request.getRequestURL());
        } else {
            try {
                long start = Long.parseLong(request.getAttribute("_START_TIME").toString());
                log.info("response [{}] =>[{}] [{}] cost[{}]ms",
                        IpUtils.getIpAddress(request),
                        request.getMethod(),
                        request.getRequestURL(),
                        System.currentTimeMillis() - start);
            } catch (NullPointerException e) {
                log.info("response [{}] =>[{}] [{}]",
                        IpUtils.getIpAddress(request),
                        request.getMethod(),
                        request.getRequestURL());
            }
        }

    }

//    @AfterReturning("webLog()")
    public void  doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
        log.info("WebLogAspect.doAfterReturning()");

    }
}
