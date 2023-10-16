package com.bobo.controller;

import com.bobo.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author BoBoKing
 * @date 2023/5/25
 *
*  关于 @ResponseBody
*  类型：方法注解、类注解
*  位置：Controller方法上/类上
*  作用：将方法返回值直接响应，如果返回值类型是 实体类对象/集合，将会转换为 JSON格式响应
*  说明：@RestController = @Controller + @ResponseBody 相当于两个注解合二为一，作用等价
 *  @ResponseBody 注解会把方法的返回值转化为JSON格式数据，然后再响应回去
 */
@RestController
public class RequestController {

    //原始方式
//    @RequestMapping("/simpleParam")
//    public String simpleParam(String name, Integer age) {
//        // 获取请求参数
//        System.out.println(name + ":" + age);
//
//        return "OK";
//    }

    /**
     简单参数
     */
    //如果方法形参名称与请求参数名称不匹配，可以使用 @RequestParam 完成映射
    //@RequestParam中的required属性默认为 true，代表该请求参数必须传递，如果不传递会报错
    //如果该参数是可选的，可以将 required属性设置为 false
    @RequestMapping("/simpleParam")
    public String simpleParam(@RequestParam(name = "name", required = true) String username, Integer age) {
        // 获取请求参数
        System.out.println(username + ":" + age);

        return "简单参数 OK";
    }

    /**
     * 实体参数  @RequestMapping指定请求路径
     * 获取请求参数,并封装到对象user中，之一请求的参数名要与形参实体类的属性名对应
     */
    @RequestMapping("/pojoParam")
    public String pojoParam(User user) {
        System.out.println(user);

        return "实体参数 OK";
    }

    /**
     * 数组参数
     * 请求参数名与形参数组名称相同且请求参数为多个，定义数组类型形参即可接收参数
     */
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));

        return "数组参数 OK";
    }

    /**
     * 集合参数
     * 请求参数名与形参集合名称相同且请求参数为多个
     * 注意需要使用注解 @RequestParam 来绑定集合的参数关系，否则再多个值的情况下，它会默认封装到数组里
     */
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);

        return "集合参数 OK";
    }

    /**
     * 日期参数
     * 使用 @DateTimeFormat 注解完成日期参数格式转换
     */
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime) {
        System.out.println(updateTime);

        return "日期参数 OK";
    }

    /**
     * JSON参数      @ResponseBody 注解会把方法的返回值转化为JSON格式数据，然后再响应回去
     * 使用 @RequestBody 注解标识
     * JSON数据键名key 与形参对象属性名对应，定义pojo实体对象形参即可接收参数
     */
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);

        return "JSON参数 OK";
    }

    /**
     * 路径URL参数  单个获取
     * 使用 @PathVariable 注解标识
     * 通过请求URL直接传递参数，使用 {...} 来标识该路径参数，需要使用 @PathVariable获取路径参数
     */
    @RequestMapping("/path/{id}")
    public String urlParam(@PathVariable Integer id) {
        /*
        * 比如客户端发起请求路径为 http://localhost:8080/path/100
        * 则此处 id 输出为 100
        * */
        System.out.println(id); // 100

        return "路径URL参数 OK";
    }

    /**
     * 路径URL参数  多个获取
     * 通过请求URL直接传递参数，使用 {...} 来标识该路径参数，需要使用 @PathVariable获取路径参数
     */
    @RequestMapping("/path/{id}/{name}")
    public String urlsParam(@PathVariable Integer id, @PathVariable String name) {
        /* 使用{}之间用/分隔开，id代表请求路径中的第一个参数{id}， name --> {name}
         * 比如客户端发起请求路径为 http://localhost:8080/path/100/Tom
         * 则此处输出为 100:Tom
         */
        System.out.println(id + ":" + name); // 100:Tom

        return "路径URL参数 OK";
    }
}
