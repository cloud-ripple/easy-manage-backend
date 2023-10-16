package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author boboking
 * @date 2023/5/31
 * @description 响应结果统一封装类
 */
//lombok是一个实用的Java类库，能通过注解的形式自动生成构造器、getter、setter、equals、hashcode、toString等方法
/*
    @Data 注解提供了综合的生成代码功能 (等价于注解 @Getter + @Setter + @ToString + @EqualsAndHashCode)
    @NoArgsConstructor 注解为实体类生成无参的构造器
    @AllArgsConstructor 为实体类生成除了static修饰的字段之外带有各参数的构造方法
    注意：Lombok会在编译时，自动生成对应的java代码，使用lombok时，还需要安装一个lombok的插件(idea自带，不需要下载)
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code; //响应码，1 成功；0 失败
    private String msg; //响应信息 描述字符串
    private Object data; //返回的数据

    public static Result success() {  //增删改 成功响应
        return new Result(1, "success", null);
    }

    public static Result success(Object data) { //查询 成功响应
        return new Result(1, "success", data);
    }

    public static Result error(String msg) { //失败响应
        return new Result(0, msg, null);
    }
}
