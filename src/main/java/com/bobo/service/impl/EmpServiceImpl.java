package com.bobo.service.impl;

import com.bobo.mapper.EmpMapper;
import com.bobo.pojo.Emp;
import com.bobo.pojo.Log;
import com.bobo.pojo.PageBean;
import com.bobo.service.EmpService;
import com.bobo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */
@Service //将当前实现类交给IOC容器管理，成为容器中的bean对象，通过依赖注入就可拿到该实体类对象
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private LogService logService;

    //分页条件查询
    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.getAllEmp(name, gender, begin, end, start, pageSize);
        Long total = empMapper.total();
        PageBean pageBean = new PageBean(total, empList);

        return pageBean;
    }

    //根据id批量删除
    @Override
    public int delete(List<Integer> ids) {
        int i = empMapper.deleteByIds(ids);
        if (i > 0) {
            Log log = new Log();
            log.setCreateTime(LocalDateTime.now());
            log.setDescription("删除了 " + ids.size() + " 个员工");
            logService.insert(log); //记录日志
        }
        return i;
    }

    //根据员工对象添加数据
    @Override
    public int addByEmp(Emp emp) {
        return empMapper.insert(emp);
    }

    //更新修改员工信息
    @Override
    public int updateByEmp(Emp emp) {
        int i = empMapper.update(emp);
        if (i > 0) {
            Log log = new Log();
            log.setCreateTime(LocalDateTime.now());
            log.setDescription("名为 " + emp.getName() + " 的员工信息发生了变更");
            logService.insert(log); //记录日志
        }
        return i;
    }

    //根据id查询员工
    @Override
    public Emp selectById(Integer id) {
        return empMapper.select(id);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

}
