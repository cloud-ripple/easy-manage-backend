package com.bobo.controller;

import com.bobo.pojo.Log;
import com.bobo.pojo.PageBean;
import com.bobo.pojo.Result;
import com.bobo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/6/30
 * @description 日志管理
 */
@Slf4j
@RestController
@RequestMapping
public class LogController {

    @Autowired
    LogService logService;


    //日志分页条件查询
    @GetMapping("/logs")
    public Result page(@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer currentPage,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        // 统计所有日志数量，即一共多少条记录
        Long total = logService.countLogs();
        List<Log> rows = logService.getLogs(begin, end, currentPage, pageSize);

        PageBean pageBean = new PageBean(total, rows);

        return Result.success(pageBean);
    }


    //根据 id 删除日志记录
    @DeleteMapping("/logs/{ids}")  /** 注意这里的请求路径中的参数 ids 必须与下面方法中的形参名相同，否则会报错 Required URI template variable 'id' for method parameter type Integer is not present */
    public Result deleteById(@PathVariable List<Integer> ids) {
        logService.deleteLog(ids);

        return Result.success();
    }
}
