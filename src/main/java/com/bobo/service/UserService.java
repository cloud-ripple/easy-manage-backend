package com.bobo.service;

import com.bobo.pojo.PageBean;
import com.bobo.pojo.User;

import java.util.List;

/**
 * @author boboking
 * @date 2023/7/3
 * @description
 */
public interface UserService {

    /**
     * 用户注册
     * @param user 前端提交的注册表单数据封装到 user对象中
     */
    int userRegister(User user);


    /**
     * 用户分页条件查询
     * @return 查询页
     */
    PageBean page(String nickname, String age, String address, String role, String gender,
                  Integer currentPage, Integer pageSize);

    /**
     * 根据id查询用户
     * @return 单个用户
     */
    User getUserById(Integer id);


    /**
     * 更新用户信息
     * @param user 前端提交的表单参数
     * @return
     */
    int updateByUser(User user);


    /**
     * 删除用户，也可以批量删除
     * @param ids 多个id
     * @return
     */
    int deleteUser(List<Integer> ids);


    /**
     * 用户登录
     * @param user 前端提交的登录表单参数
     * @return
     */
    User login(User user);
}
