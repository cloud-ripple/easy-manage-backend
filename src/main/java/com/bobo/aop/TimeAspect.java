package com.bobo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author boboking
 * @date 2023/6/5
 * @description 统计各个层的方法执行耗时
 */

@Slf4j //打印日志
@Component //交给IOC管理
@Aspect //该注解代表当前类是一个 AOP类
public class TimeAspect {

    // 对应举例：com.bobo.service.impl.DeptServiceImpl.getAllDept
    @Around("execution(* com.bobo.service.*.*(..))")  //切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录开始时间
        long begin = System.currentTimeMillis();

        // 2. 调用原始方法运行
        Object result = joinPoint.proceed();  //result 原始方法的返回值

        // 3. 记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + " 方法执行耗时：{} ms", (end - begin));
        //getSignature() 拿到原始方法签名

        return result;
    }
}
