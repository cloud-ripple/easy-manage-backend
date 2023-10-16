package com.bobo.service.impl;

import com.bobo.mapper.DeptMapper;
import com.bobo.mapper.EmpMapper;
import com.bobo.pojo.Dept;
import com.bobo.pojo.Log;
import com.bobo.service.LogService;
import com.bobo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */
//@Component //将当前实现类交给IOC容器管理，成为容器中的bean对象，通过依赖注入就可拿到该实体类对象
@Service //也可以使用@Service注解，效果一样
public class DeptServiceImpl implements DeptService {

    @Autowired  //依赖注入
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private LogService logService;

    @Override
    public List<Dept> getAllDept() {
        return deptMapper.getAllDept();
    }

    /*
    6. spring事务管理
    事务：是一组操作的集合，它是一个不可分割的工作单位，这些操作 要么同时成功，要么同时失败。
    操作：
    开启事务(一组操作开始前，开启事务): start transaction / begin;
    提交事务(这组操作全部成功后，提交事务): commit ;
    回滚事务(中间任何一个操作出现异常，回滚事务): rollback ;

    案例：
    解散部门：删除部门，同时删除该部门下的员工

    6.1 spring事务管理
    注解：@Transactional
    位置：业务层Service 方法上、类上、接口上  -- 因为业务层中一个业务功能(方法)可能会涉及多个数据访问的操作
    如果在方法上声明该注解，则代表把该方法交给spring进行事务管理，在类上，代表把该类的所有方法都交给spring事务管理
    在接口上，则该接口的所有实现类的方法都交给spring事务管理
    作用：将当前方法交给spring进行事务管理，方法执行前，底层会自动开启事务，成功执行完毕，提交事务，出现异常，回滚事务。

    注解：@Transactional 一般选择加在业务层Service的增删改方法上(并且该方法有多个数据访问的操作)，因为查询操作并不会影响数据的变更，无须控制事务
    */
    /** 事务的 propagation属性 事务传播行为：指的就是当一个事务方法被另一个事务方法调用时，这个事务方法应该如何进行事务控制
     * 属性值 REQUIRED :[默认值]需要事务，有则加入，无则创建新事务
     * 属性值 REQUIRES_NEW : 需要新事务，无论有无，总是创建新事务
     *
     * 需求：解散部门时，无论是成功还是失败，都要记录操作日志 。
     * 步骤：
     * 1. 解散部门：删除部门、删除部门下的员工
     * 2. 记录日志到数据库表中
     * REQUIRED: 大部分情况下都是用该传播行为即可
     * REQUIRES_NEW: 当我么不希望事务之间相互影响时，可以使用该传播行为。比如：下订单前需要记录日志，不论订单保存成功与否
     * 都需要保证日志记录能够记录成功 */

    //@Transactional  //该注解将当前方法，交给spring进行事务管理，一旦出现运行时异常，会自动进行事务的回滚
    @Transactional(rollbackFor = Exception.class)  /** 默认情况下，只有出现 RuntimeException 运行时异常，才回滚。事务的 rollbackFor 属性用于控制出现何种异常类型，回滚事务 */
    @Override   /** Exception.class 代表任何类型的异常，都会回滚事务 */
    public int deleteById(Integer id) throws Exception {
        try {
            deptMapper.delete(id); //根据ID删除部门数据
            //假设部门已经删除、解散了，那么该部门下的员工也要删除
            //int i = 1 / 0; //算术异常 属于运行时异常
            /** 此处会抛出一个运行时的算术异常，下面的所有代码不再执行 ，也就意味着删除部门时发生了异常，
             * 而该部门下的员工却没有正常删除 ，导致数据不完整，不一致
             * 解决方法：让这两个操作处于同一事务中，要么同时成功，要么同时失败 */
//            if (true) {
//                throw new Exception("出错啦..."); //此处并非运行时异常，并不会回滚事务，即会发生部门已经删除，但是该部门员工数据还在
//            }
            return empMapper.deleteByDeptId(id); //根据部门id来删除该部门下的员工数据
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //记录部门日志
            Log log = new Log();
            log.setCreateTime(LocalDateTime.now());
            log.setDescription("执行了删除部门的操作，此次删除的是 " + id + " 号部门");
            logService.insert(log); //记录部门日志  insert()方法上也添加了事务注解
            //并且设置事务属性值 @Transactional(propagation = Propagation.REQUIRES_NEW)  //需要新事务，无论有无，总是创建新事务
        }
    }

    @Override
    public int insertByName(String name) {
        return deptMapper.insert(name);
    }

    @Override
    public int updateById(String name, Integer id) {
        int rows = deptMapper.update(name, id);
        if (rows > 0) {  //记录日志 部门信息被修改
            logService.insert(new Log(null, LocalDateTime.now(), id + " 号部门的名称被修改为 " + name));
        }
        return rows;
    }

    @Override
    public Dept selectById(Integer id) {
        return deptMapper.select(id);
    }

}
