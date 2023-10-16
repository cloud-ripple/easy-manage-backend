package com.bobo.controller;

import com.bobo.pojo.PageBean;
import com.bobo.pojo.Result;
import com.bobo.pojo.User;
import com.bobo.service.UserService;
import com.bobo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author boboking
 * @date 2023/7/3
 * @description 用户管理
 */

@Slf4j
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("注册用户信息：{}", user);
        int rows = userService.userRegister(user);
        if (rows < 0) {
            return Result.error("注册失败!");
        }
        return Result.success();
    }

    //用户分页查询
    @GetMapping("/users")
    public Result pageUser(String nickname, String age, String address, String role, String gender,
        @RequestParam(defaultValue = "1") Integer currentPage,
        @RequestParam(defaultValue = "20") Integer pageSize) {
        /** 注意这里必须指定后面两个参数有默认值 否则在进行逻辑处理
         * startIndex = pageSize * (currentPage - 1); //分页查询 起始索引 = 页展示记录数 * (当前页码 - 1) 时，而前端却没有指定参数值
         * 导致所有参数为 null 直接产生空指针异常报错 */
        log.info("分页查询，参数：{}, {}, {}, {}, {}, {}, {}", nickname, age, address, role, gender, currentPage, pageSize); //输出日志

        PageBean page = userService.page(nickname, age, address, role, gender, currentPage, pageSize);
        return Result.success(page);
    }

    //根据 ID 查询用户信息，回显
    @GetMapping("/users/{hh}")
    public Result selectById(@PathVariable Integer hh) {
        User user = userService.getUserById(hh);

        return Result.success(user);
    }

    //用户编辑更新
    @PutMapping("/users")
    public Result updateUser(@RequestBody User user) {
        log.info("更新用户: {}", user);
        userService.updateByUser(user);

        return Result.success();
    }

    //删除用户，也可以批量删除
    @DeleteMapping("/users/{ids}")
    public Result deleteUser(@PathVariable List<Integer> ids) {
        userService.deleteUser(ids);

        return Result.success();
    }

    //添加用户
    @PostMapping("/users")
    public Result addUser(@RequestBody User user) {
        log.info("添加用户信息：{}", user);
        userService.userRegister(user);

        return Result.success();
    }

    //根据前端请求参数 token,来获取当前登录用户的信息
    @GetMapping("/users/info")
    public Result getInfoByToken(String token) {
        log.info("用户信息 token: {}", token);
        //解析前端传递过来的 Token (即 JWT令牌，在登录成功下发令牌时，里面封装了用户 id, username等信息)
        Claims claims = JwtUtils.parseJWT(token); // Claims extends Map<String, Object> 这里的 Claims其实就是一个 Map集合
        //根据 key 获取 value
        Integer id = (Integer) claims.get("id");
        //根据 id查询用户相关信息，并响应给前端
        User user = userService.getUserById(id);

        return Result.success(user);
    }
}
