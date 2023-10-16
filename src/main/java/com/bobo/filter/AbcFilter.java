package com.bobo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author boboking
 * @date 2023/6/3
 * @description
 */

//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    //init(), destroy()方法是默认执行的，可以根据需要重写一个 doFilter方法即可
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Abc 拦截到了请求...放行前的逻辑");
        //放行，让其去访问对应的资源 如果不放行是访问不到该请求和响应的资源的
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Abc 拦截到了请求...放行后的逻辑");
    }
}
