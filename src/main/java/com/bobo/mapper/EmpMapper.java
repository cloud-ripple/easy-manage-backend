package com.bobo.mapper;

import com.bobo.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */
@Mapper //将当前实现类交给IOC容器管理，成为容器中的bean对象，通过依赖注入就可拿到该实体类对象
public interface EmpMapper {
    /**
     * 查询所有员工信息
     * @return
     */
    @Select("select count(*) from tb_emp")
    public Long total();

    public List<Emp> getAllEmp(String name, Short gender, LocalDate begin, LocalDate end, Integer start, Integer pageSize);

    int deleteByIds(List<Integer> ids);

    @Options(keyProperty = "id", useGeneratedKeys = true) //获取返回的主键
    int insert(Emp emp);

    int update(Emp emp);

    @Select("select * from tb_emp where id = #{id}")
    Emp select(Integer id);

    /**
     * 根据用户名和密码查询员工
     * @param emp 员工对象
     * @return 查询结果，单个记录
     */
    @Select("select * from tb_emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id来删除该部门下的员工数据
     */
    @Delete("delete from tb_emp where dept_id = #{deptId}")
    int deleteByDeptId(Integer deptId);
}
