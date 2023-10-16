package com.bobo.controller;

import com.bobo.pojo.Result;
import com.bobo.pojo.User;
import com.bobo.service.UserService;
import com.bobo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author boboking
 * @date 2023/6/2
 * @description 登录管理
 */
//@Controller + @ResponseBody = @RestController，@ResponseBody 注解会把方法的返回值转化为JSON格式数据，然后再响应回去
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService; //由于登录时，只涉及用户名名、密码相关，所以只需调用 EmpService接口中的方法即可

    @PostMapping("/login")  //注意登录验证都是使用 Post 方式请求，而 GET 请求方式会直接把请求参数值显示在请求路径 url中
    public Result login(@RequestBody User user) {  /** @RequestBody 注解的作用就是把请求参数为 JSON格式的数据 封装在 emp实体类对象中 */
        //普通参数类型直接接收，而实体类型要用 @RequestBody注解声明，否则接收不到前端请求的参数
        //前端请求参数 用户名、密码 username, password 直接使用实体类来接收，因为Emp类有其属性名 和 请求参数名一致
        log.info("用户登录信息：{}", user); //打印日志信息，可以查看前端请求发送过来的参数
        User u = userService.login(user); //根据请求参数，查询得到员工

        //如果登录成功，生成令牌，下发令牌
        if (u != null) {
            Map<String, Object> claims = new HashMap<>(); //JWT第二部分负载 payload 中存储的内容, 把自定义的内容封装到map集合
            //封装员工信息到map集合，作为令牌的负载存储内容
            claims.put("id", u.getId()); //主键id
            claims.put("nickname", u.getNickname()); //昵称
            claims.put("username", u.getUsername()); //账号
            String JWT = JwtUtils.generateJwt(claims); //调用工具类方法生成JWT
            /** 给前端响应返回JWT令牌数据，并且令牌中包含了当前登录的的员工信息 */
            return Result.success(JWT);
        }
        //否则登录失败，直接返回错误信息
        return Result.error("用户名或密码错误！");
    }
}
