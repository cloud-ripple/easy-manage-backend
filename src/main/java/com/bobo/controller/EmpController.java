package com.bobo.controller;

import com.bobo.pojo.Emp;
import com.bobo.pojo.Result;
import com.bobo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description 员工管理
 */
//@Controller + @ResponseBody = @RestController，@ResponseBody 注解会把方法的返回值转化为JSON格式数据，然后再响应回去
@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页条件查询
    @GetMapping ("/emps")
    public Result page(String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询，参数：{}, {}, {}, {}", begin, end, page, pageSize); //输出日志
        //调用service方法

        return Result.success(empService.page(name, gender, begin, end, page, pageSize));
    }

    //根据主键id批量删除
    @DeleteMapping("/emps/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids) {
//        System.out.println(ids);
        int i = empService.delete(ids);

        return Result.success();
    }

    //添加员工
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp) {
        empService.addByEmp(emp);

        return Result.success();
    }

    /** 查询员工，根据单个id  查询回显---> 修改编辑员工 */
    @GetMapping("/emps/{id}")
    public Result selectById(@PathVariable Integer id) {
        Emp emp = empService.selectById(id);

        return Result.success(emp);
    }

    //修改编辑员工
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("更新的员工信息：{}", emp);
        empService.updateByEmp(emp);

        return Result.success();
    }


}
