package com.bobo.service.impl;

import com.bobo.mapper.UserMapper;
import com.bobo.pojo.PageBean;
import com.bobo.pojo.User;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author boboking
 * @date 2023/7/3
 * @description
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户注册
    @Override
    public int userRegister(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public PageBean page(String nickname, String age, String address, String role, String gender,
                         Integer currentPage, Integer pageSize) {
        Integer startIndex = pageSize * (currentPage - 1); //分页查询 起始索引 = 页展示记录数 * (当前页码 - 1)
        Long total = userMapper.countUsers(); //统计所有用户总记录条数
        List<User> rows = userMapper.getAllUsers(nickname, age, address, role, gender, startIndex, pageSize); //根据条件查询所有用户
        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setTotal(total);
        userPageBean.setRows(rows);

        return userPageBean;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateByUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteUser(List<Integer> ids) {
        return userMapper.deleteByIds(ids);
    }

    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }
}
