package com.bobo.controller;

import com.bobo.pojo.Dept;
import com.bobo.pojo.Result;
import com.bobo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description 部门管理
 */
//@Slf4j  //也可以使用 lombok 依赖提供的注解 @Slf4j，就可以直接调用日志对象中的方法
/*      @Controller + @ResponseBody = @RestController，
        @ResponseBody 注解会把方法的返回值转化为JSON格式数据，然后再响应回去
*/
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    //日志对象 使用注解之后就可以不用创建了
    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //指定请求路径为 /depts，请求方式GET

    /** 上面哪个注解就可以省略，
     * 该注解是@RequestMapping的衍生注解，限定请求方式为GET，如果客户端发起了其它请求方式则会报错405请求方式不允许 */
    //类似还有 @PostMapping @PutMapping @DeleteMapping
    @GetMapping("/depts")
    public Result findAll() {
//        System.out.println("--------查询全部部门数据如下：-----");
        //采用日志方式，而不是直接在控制台打印数据
        log.info("-----查询全部部门数据如下:-------");
        /** 调用service查询部门数据 */
        List<Dept> deptList = deptService.getAllDept();
//        deptList.stream().forEach(dept -> {
//            System.out.println(dept);
//        });

        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping("/depts/{id}")
    public Result deleteById(@PathVariable Integer id) throws Exception {
        int i = deptService.deleteById(id);

        return Result.success();
    }

    //添加部门
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        int i = deptService.insertByName(dept.getName());

        return Result.success();
    }

    //修改部门 根据id name
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        int i = deptService.updateById(dept.getName(), dept.getId());

        return Result.success();
    }

    //根据id查询部门
    @GetMapping("/depts/{id}")
    public Result selectById(@PathVariable Integer id) {
        Dept dept = deptService.selectById(id);

        return Result.success(dept);
    }
}
