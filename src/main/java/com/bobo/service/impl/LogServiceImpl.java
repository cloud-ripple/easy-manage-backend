package com.bobo.service.impl;

import com.bobo.mapper.LogMapper;
import com.bobo.pojo.Log;
import com.bobo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/6/5
 * @description
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)  //需要新事务，无论有无，总是创建新事务
    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }

    @Override
    public List<Log> getLogs(LocalDate begin, LocalDate end, Integer currentPage, Integer pageSize) {
        Integer startIndex = pageSize * (currentPage - 1);
        return logMapper.page(begin, end, startIndex, pageSize);
    }

    @Override
    public Long countLogs() {
        return logMapper.count();
    }

    @Override
    public void deleteLog(List<Integer> ids) {
        logMapper.deleteByIds(ids);
    }

}
