package com.bobo.mapper;

import com.bobo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author boboking
 * @date 2023/6/2
 * @description
 */
@Mapper
public interface StudentMapper {

    @Select("select * from student")
    List<Student> select();
}
