package com.bobo.service;

import com.bobo.pojo.Log;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/6/5
 * @description
 */
public interface LogService {
    /**
     * 记录部门日志
     * @param log
     */
    void insert(Log log);

    /**
     * 分页条件查询日志
     * @param begin
     * @param end
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Log> getLogs(LocalDate begin, LocalDate end, Integer currentPage, Integer pageSize);


    /**
     * 统计日志数量
     * @return
     */
    Long countLogs();


    /**
     * 根据id删除日志记录
     * @param ids
     */
    void deleteLog(List<Integer> ids);
}
