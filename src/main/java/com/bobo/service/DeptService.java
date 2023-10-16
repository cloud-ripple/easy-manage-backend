package com.bobo.service;

import com.bobo.pojo.Dept;

import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */

public interface DeptService {
    /**
     * 得到所有部门信息
     * @return
     */
    public List<Dept> getAllDept();

    /**
     * 根据id删除部门
     * @param id 主键id
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 根据部门名插入数据
     * @param name
     * @return
     */
    int insertByName(String name);

    /**
     * 根据id更新部门信息
     *
     * @param name 部门名
     * @param id   主键id
     * @return
     */
    int updateById(String name, Integer id);

    /**
     * 根据部门id查询
     * @param id
     * @return
     */
    Dept selectById(Integer id);
}
