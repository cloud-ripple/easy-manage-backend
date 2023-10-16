package com.bobo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author boboking
 * @date 2023/6/5
 * @description
 */

@Slf4j //打印日志
@Component //交给IOC管理
@Aspect // AOP类
public class MyAspect1 {

    //前置通知
    @Before("execution(* com.bobo.service.impl.DeptServiceImpl.*(..))")
    public void before() {
        log.info("before ..");
    }

    //环绕通知
    @Around("execution(* com.bobo.service.impl.DeptServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before .."); //环绕前

        //打调用目标对象的原始方法执行
        Object result = joinPoint.proceed();

        log.info("around after .."); //环绕后

        return result;
    }

    //后置通知
    @After("execution(* com.bobo.service.impl.DeptServiceImpl.*(..))")
    public void after() {
        log.info("after ..");
    }

    //返回后通知 原始方法执行完毕并正常返回值(执行过程中有异常，就不会执行这个 afterReturning()方法 )
    @AfterReturning("execution(* com.bobo.service.impl.DeptServiceImpl.*(..))")
    public void afterReturning() {
        log.info("afterReturning ...");
    }


    //异常抛出后通知  目标原始方法出现异常时才会执行
    @AfterThrowing("execution(* com.bobo.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowing() {
        log.info("afterThrowing ...");
    }

}
