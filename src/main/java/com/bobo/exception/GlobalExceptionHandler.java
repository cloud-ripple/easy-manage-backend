package com.bobo.exception;

import com.bobo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author boboking
 * @date 2023/6/4
 * @description 全局异常处理器
 * 异常处理
 * 三层架构调用顺序： Controller层(请求响应) ----调用---> Service层(逻辑处理) ---->调用----> Mapper层(数据操作)
 * 一旦Mapper层出现了异常，即数据库操作出现了问题，比如：sql语法错误。此时会把产生的异常抛给调用者 Service层，而该层也没有做异常处理
 * 接着把异常往上抛给Controller层，而该层也不做异常处理，而是交给全局异常处理器来处理----> 处理完成后，再给前端响应标准统一的响应结果 Result(封装相关错误信息)。
 * <p>
 * 方案一：在Controller的方法中进行 try...catch处理   --- 代码臃肿，不推荐，因为有很多方法
 * 方案二：全局异常处理器  --- 简单、优雅、推荐
 */
/*  @RestControllerAdvice = @ControllerAdvice + @ResponseBody 相当于两个注解合二为一
    @ResponseBody 注解会把方法的返回值转化为JSON格式数据，然后再响应回去
 */

// 下面这个 @RestControllerAdvice 注解等价于 @ControllerAdvice + @ResponseBody
// @ResponseBody 注解的作用是将java对象通过适当的转换器转换为指定的格式之后，写入到Response对象的Body区，通常用来返回JSON数据或者是XML数据
//效果等同于通过Response对象输出指定格式的数据。
@Slf4j  //打印日志     //用于指定要对项目中的那些类进行异常处理，可以填多个。没有指定的类就不对它进行异常处理。
@RestControllerAdvice(annotations = {RestController.class, Controller.class}) //需要在类上加上注解 RestControllerAdvice来声明全局异常处理
public class GlobalExceptionHandler {

    //在方法上加上ExceptionHandler 注解指定要捕获那种类型的异常
//    @ExceptionHandler(Exception.class)  //Exception.class代表捕获所有类型的异常
//    public Result allException(Exception ex) {
//        log.error("在全局异常中捕获到：{}", ex.getMessage());//输出日志
//
//        return Result.error("对不起，操作失败，请联系管理员！");
//    }

    //捕获SQL 完整性约束(constraint) 违反异常 比如插入数据时由于 账号等某个字段有唯一约束，插入重复数据时会产生语法错误
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class}) //可以在 {} 中指定多个异常类型
    public Result constraintException(SQLIntegrityConstraintViolationException e) {
        log.error("在全局异常中捕获到完整性约束违反异常：{}", e.getMessage());//输出日志 比如： Duplicate entry 'bobo' for key 'username'
        //代表 username字段唯一约束，插入数据时 bobo 值与其重复，违反约束异常
        //判断抛出的异常信息里是否包含这两个单词（这种索引唯一发生重复时的异常信息里都包含这两个单词）
        if (e.getMessage().contains("Duplicate entry")) {
            //如果是这种异常，就把用户输入的重复字段的值提取出来，然后返回。
            String[] split = e.getMessage().split(" ");
            log.error(split[2] + " 已存在"); //控制台输出错误日志

            return Result.error(split[2] + " 已存在!"); //给前端响应
        }

        //如果不是这种异常，就返回未知错误。
        return Result.error("未知异常错误，请联系管理员！");
    }
}
