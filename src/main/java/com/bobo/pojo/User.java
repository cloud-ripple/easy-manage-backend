package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author boboking
 * @date 2023/5/29
 * @description 用户
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
public class User {
    private Integer id; //id
    private String nickname; //昵称
    private String username; //账号
    private String password; //密码
    private String role; //角色
    private String gender; //性别
    private Integer age; //年龄
    private String phone; //电话
    private String address; //地址
    private String email; //邮箱
    private String avatar; //头像
    private LocalDateTime registerDate; //注册日期
}
