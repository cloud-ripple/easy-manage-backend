package com.bobo.config;

import com.bobo.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author boboking
 * @date 2023/6/4
 * @description 拦截器配置类
  拦截路径             含义                      举例
   /*               一级路径                能匹配 /depts, /emps, /login, 不能匹配 /depts/1
   /**              任意级路径               能匹配 /depts, /depts/1, /depts/1/2
   /deptes/*       /depts下的一级路径         能匹配 /depts/1, 不能匹配 /depts/1/2, /depts
   /depts/**       /depts下的任意级路径        能匹配 /depts, /depts/1, /depts/1/2, 不能匹配 /emps/1

 */

@Configuration  //该注解代表当前类是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired  //依赖注入，从IOC容器中获取该类的对象实例bean
    private LoginCheckInterceptor loginCheckInterceptor;

    //注册拦截器  拦截器可以根据需求，配置不同的拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(loginCheckInterceptor);
        interceptor.addPathPatterns("/**").excludePathPatterns("/login","/register", "/upload"); //注意在拦截器中拦截所有 使用两个 ** 符号
                    //在拦截所有请求路径资源时------------但不需要拦截哪些(请求路径为 /login)资源
        /** 把 /login 路径请求资源排除，即发送登录请求时不会发生拦截，可以直接登录 */
    }
}
