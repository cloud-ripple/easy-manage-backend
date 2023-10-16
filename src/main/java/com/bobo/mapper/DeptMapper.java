package com.bobo.mapper;

import com.bobo.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */
@Mapper //将当前实现类交给IOC容器管理，成为容器中的bean对象，通过依赖注入就可拿到该实体类对象
public interface DeptMapper {

    /**
     * 查询所有部门信息
     * @return 集合
     */
    @Select("select * from tb_dept")
   public List<Dept> getAllDept();

    /**
     * 根据主键id删除部门
     * @param id
     * @return
     */
    @Delete("delete from tb_dept where id = #{id}")
    int delete(Integer id);

    /**
     * 根据部门名插入数据
     * @param name 部门名
     * @return
     */
    @Insert("insert into tb_dept values (null, #{name}, now(), now())")
    int insert(String name);

    /**
     * 根据主键id更新部门信息
     *
     * @param name 部门名
     * @param id   主键
     * @return
     */
    @Update("update tb_dept set name = #{name} where id = #{id}")
    int update(String name, Integer id);

    /**
     * 根据主键id查询部门
     * @param id
     * @return
     */
    @Select("select * from tb_dept where id = #{id}")
    Dept select(Integer id);
}
