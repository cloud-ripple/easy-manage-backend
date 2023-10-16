package com.bobo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author boboking
 * @date 2023/6/3
 * @description Filter拦截器快速入门
 * 1. 定义Filter: 定义一个类，实现Filter接口，并重写其所有方法
 * 2. 配置Filter: Filter类加上 @WebFilter注解，配置拦截资源的路径
 * SpringbootApplication启动引导类加上 @ServletComponentScan 注解开启Servlet组件支持。
 *
 * 3. Filter拦截路径 可以根据需求，配置不同的拦截资源路径
 * 拦截路径          urlPatterns值               含义
 * 拦截具体路径       /login              只有访问 /login 路径时，才会被拦截
 * 目录拦截         /emps/*                 访问 /emps下的所有资源，即以 /emps开头的请求路径，都会被拦截
 * 拦截所有         /*                    请求访问所有资源，都会被拦截
 *
 * 4. 过滤器链
 * 一个Web应用中，可以配置多个过滤器，这多个过滤器就形成了一个过滤器链
 */

//@WebFilter(urlPatterns = "/*")  //默认拦截所有路径请求， 注释掉就不会起拦截作用了
public class DemoFilter implements Filter {
    //初始化方法，Web服务器启动，创建Filter时调用，只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Demo init 初始化方法执行了");
    }

    //拦截到请求时，调用该方法，可调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo 拦截到了请求...放行前的逻辑");
        //放行，让其去访问对应的资源 如果不放行是访问不到该请求和响应的资源的
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Demo 拦截到了请求...放行后的逻辑");
    }

    //销毁方法，服务器关闭时调用，只调用一次
    @Override
    public void destroy() {
        System.out.println("Demo destroy 销毁方法执行了");
    }
}
