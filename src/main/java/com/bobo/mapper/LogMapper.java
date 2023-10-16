package com.bobo.mapper;

import com.bobo.pojo.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/6/5
 * @description
 */
@Mapper
public interface LogMapper {
    /**
     * 记录部门日志
     */
    @Insert("insert into log values (null, #{createTime}, #{description})")
    void insert(Log log);

    /**
     * 分页条件查询日志
     * @param begin 开始时间
     * @param end
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Log> page(LocalDate begin, LocalDate end, Integer startIndex, Integer pageSize);

    /**
     * 统计日志数量
     * @return
     */
    @Select("select count(*) from log")
    Long count();

    /**
     * 根据id删除日志
     * @param ids
     */
    void deleteByIds(List<Integer> ids);
}
