package com.sky.xljgps.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 *  description:
 *  暂时只用于监视controller的执行
 *  author:  hefeng
 *  create:  18-7-11 下午7:57
 */

@Aspect
@Component
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.sky.xljgps.controller.PersonController.*(..))")
    public void log()
    {
        logger.info("start..........");     // will not print
    }

    @Before("log()")
    public void deBefore(JoinPoint joinPoint)
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("deBefore.......");
        logger.info("url={}", request.getRequestURL());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter()
    {
        logger.info("doAfter.......");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterRetuning(Object object)
    {
        logger.info("doAfterRetuning  response={}", object);
    }
}
