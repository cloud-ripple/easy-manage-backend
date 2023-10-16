package com.bobo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bobo.pojo.Result;
import com.bobo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boboking
 * @date 2023/6/4
 * @description 登录校验 Interceptor 拦截器
 *
 * 过滤器Filter 与 拦截器 Interceptor 区别
 *  接口规范不同：过滤器需要实现 Filter接口，而拦截器需要实现 HandlerInterceptor接口
 *  拦截范围不同：过滤器 Filter会拦截所有的资源，而拦截器 Interceptor只会拦截Spring环境中的资源
 */

//定义一个拦截器，并实现 HandlerInterceptor接口

@Slf4j  //日志使用
@Component //将该类交给IOC容器管理，成为IOC中的bean对象
public class LoginCheckInterceptor implements HandlerInterceptor {

    //由于 HandlerInterceptor 接口中，三个方法都有默认实现，所以需要按住快捷键 Ctrl + o 即可弹出重写方法

    //该方法在目标资源方法(即 Controller控制器中的方法)运行前 运行，返回true: 放行   返回false: 不放行 (就不会去执行控制器当中的方法)
    /** 登录校验因该在 preHandle()方法中进行，否则 Controller中的方法都已经执行了，那还有必要做登录校验吗？ 所以要在Controller方法运行之前来做登录校验  */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle方法执行了"); //在执行Controller中的方法时，先执行该方法，如果返回 true了才会去执行对应的目标资源方法
        // 比如浏览器发起的请求路径为 /emps 时，且在拦截器中注册并添加了拦截路径 /emps , 此时不会立即响应对应路径下的资源，而是优先执行preHandle() 方法
        // 当经过一些业务逻辑处理后，并且返回true时，才会去执行Controller中对应的请求路径方法。

        //1. 获取请求url
        String url = request.getRequestURI().toString();//拿到请求的路径url地址
        log.info("请求的url: {}", url);

        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，直接放行
        if (url.contains("login")) {
            log.info("登录操作，已放行...");
            //返回true 代表放行 执行Controller中的目标资源方法 根据请求路径访问响应数据
            return true; //此时直接返回，不再执行下面的逻辑
        }

        //3. 如果不是登录操作，获取请求头中的令牌(token)
        String jwt = request.getHeader("token");  //token中携带的参数就是 JWT令牌

        //4. 判断令牌是否存在，如果不存在(代表未登录)，返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //通过JSON格式转换工具----> 阿里巴巴fastJSON(需要在 pom.xml 中引入相关依赖)，把对象转换为JSON格式的字符串
            String s = JSONObject.toJSONString(error);
            //通过HttpServletResponse对象获取输出流，写入未登录的信息，向浏览器响应
            response.getWriter().write(s);
            //返回false代表不放行，不能让它去执行Controller中的请求路径为 /login 的资源方法
            return false;
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
            //不放行
            return false;
        }

        //6. 放行 令牌存在且合法，让该请求去访问对应的响应资源
        log.info("令牌合法，放行！");
        return true; //放行 此时执行Controller中请求路径为 /login 的方法，走该方法中的相关逻辑流程
    }

    //该方法在目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle方法执行了");
    }

    //该方法在视图渲染完毕后，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion方法执行了");
    }
}
