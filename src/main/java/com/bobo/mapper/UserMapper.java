package com.bobo.mapper;

import com.bobo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author boboking
 * @date 2023/7/3
 * @description
 */

@Mapper
public interface UserMapper {

    /**
     * 用户注册添加
     * @param user
     * @return 受影响的行数
     */

    @Insert("insert into user values (null, #{nickname}, #{username}, md5(#{password}), #{role}, #{gender}, #{age}, #{phone}, #{address}, #{email}, #{avatar}, now())")
    int addUser(User user); //插入成功，则返回受影响的行数

    /**
     * 统计所有用户总记录条数
     * @return total 用户总数
     */
    @Select("select count(*) from user")
    Long countUsers();

    /**
     * 条件分页查询用户
     * @param nickname 昵称
     * @param age 年龄
     * @param address 地址
     * @param role 角色
     * @param gender 性别
     * @param startIndex 分页起始索引
     * @param pageSize 分页每页大小数
     * @return 满足条件的用户
     */
    List<User> getAllUsers(String nickname, String age, String address, String role, String gender, Integer startIndex, Integer pageSize);


    @Select("select * from user where id = #{id}")
    User selectById(Integer id);


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update(User user);


    /**
     * 删除用户。也可以批量删除
     * @param ids 多个id集合
     * @return
     */
    int deleteByIds(List<Integer> ids);


    /**
     * 根据用户名和密码查询用户
     * @return 单个user对象
     */
    @Select("select * from user where username = #{username} and password = md5(#{password})")
    User getByUsernameAndPassword(User user);
}
