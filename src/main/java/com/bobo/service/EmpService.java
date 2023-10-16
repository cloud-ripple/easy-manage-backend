package com.bobo.service;

import com.bobo.pojo.Dept;
import com.bobo.pojo.Emp;
import com.bobo.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */
public interface EmpService {
    /**
     * 得到所有员工信息,根据条件分页查询
     * @return
     */
    PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * 根据id删除，或批量删除
     * @param ids 主键id集合
     * @return
     */
    int delete(List<Integer> ids);

    /**
     * 添加员工
     * @param emp 员工对象
     * @return
     */
    int addByEmp(Emp emp);

    /**
     * 更新修改员工
     * @param emp
     */
    int updateByEmp(Emp emp);

    /**
     * 根据id查询单个员工
     * @param id
     * @return
     */
    Emp selectById(Integer id);

    /**
     * 登录 根据传入进来的参数 用户名和密码查询员工信息，完成校验
     * @param emp
     * @return 查询结果，由于用户民唯一约束，所以只会查询到一条员工记录
     */
    Emp login(Emp emp);
}
