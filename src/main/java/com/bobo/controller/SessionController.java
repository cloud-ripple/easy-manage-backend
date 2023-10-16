package com.bobo.controller;

import com.bobo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author boboking
 * @date 2023/6/2
 * @description Cookie, HttpSession演示
 */
@Slf4j
@RestController
public class SessionController {

    //设置 Cookie  服务器给浏览器响应cookie数据
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {
        response.addCookie(new Cookie("login_username", "bobo")); //设置Cookie /响应Cookie

        return Result.success();
    }

    //获取 Cookie  获取浏览器在请求头中携带的cookie数据，请求数据
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies(); //获取所有的cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_username")) { //输出name为 login_username 的Cookie
                System.out.println("login_username: " + cookie.getValue());
            }
        }

        return Result.success();
    }

    //往 HttpSession中存储值
    @GetMapping("/s1")
    public Result session1(HttpSession session) {
        log.info("HttpSession-s1: {}", session.hashCode());

        session.setAttribute("loginUser", "jack"); //往session中存储数据
        return Result.success();
    }

    //从HttpSession中获取值
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("HttpSession-s2: {}", session.hashCode());

        Object loginUser = session.getAttribute("loginUser"); //从 session中获取数据
        log.info("loginUser: {}", loginUser);
        return Result.success(loginUser);
    }

}

