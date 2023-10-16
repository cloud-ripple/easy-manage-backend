package com.bobo.filter;

import com.alibaba.fastjson.JSONObject;
import com.bobo.pojo.Result;
import com.bobo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author boboking
 * @date 2023/6/4
 * @description  登录校验 Filter 过滤器
 * 思考：
 * 所有的请求，拦截到了之后，都需要校验令牌吗？ 有一个例外，登录请求 (因为JWT令牌是在用户登录成功后才下发的)
 * 拦截到请求后，什么情况下才可以放行，执行业务操作？ 有JWT令牌，且令牌有效校验通过(合法)，否则都返回未登录错误结果
 */

@Slf4j  //用于记录日志
//@WebFilter(urlPatterns = "/*") //拦截所有请求
public class LoginCheckFilter implements Filter {
    @Override                                                                           // FilterChain(过滤器链)
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; //向下转型
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //1. 获取请求url
        String url = request.getRequestURI().toString();//拿到请求的路径url地址
        log.info("请求的url: {}", url);

        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，直接放行
        if (url.contains("login")) {
            log.info("登录操作，已放行...");
            filterChain.doFilter(request, response); //放行请求响应数据
            return; //此时直接返回，不再执行下面的逻辑
        }

        //3. 如果不是登录操作，获取请求头中的令牌(token)
        String jwt = request.getHeader("token");  //token中携带的参数就是 JWT令牌

        //4. 判断令牌是否存在，如果不存在，返回错误结果(未登录)
        if (!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");
            //通过JSON格式转换工具----> 阿里巴巴fastJSON(需要在 pom.xml 中引入相关依赖)，把对象转换为JSON格式的字符串
            String s = JSONObject.toJSONString(error);
            //通过HttpServletResponse对象获取输出流，写入未登录的信息，向浏览器响应
            response.getWriter().write(s);
            return;
        }

        //5. 如果令牌存在，解析Token(JWT令牌)，且令牌有效校验通过(合法)，如果解析失败，返回错误结果(未登录)
        try {
            JwtUtils.parseJWT(jwt);  /** 什么情况下代表JWT令牌解析成功？ 即解析时不报错，不产生异常 */
        } catch (Exception e) { //如果捕获到异常，说明解析失败
            e.printStackTrace();
            log.info("解析令牌失败，该JWT令牌不合法! 返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            //通过JSON格式转换工具----> 阿里巴巴fastJSON(需要在 pom.xml 中引入相关依赖)，把对象转换为JSON格式的字符串
            String notLogin = JSONObject.toJSONString(error);
            //通过HttpServletResponse对象获取输出流，并写入未登录的信息，向浏览器响应
            response.getWriter().write(notLogin);
            return;
        }

        //6. 放行 令牌存在且合法，让该请求去访问对应的响应资源
        log.info("令牌合法，放行！");
        filterChain.doFilter(request, response);
    }
}
