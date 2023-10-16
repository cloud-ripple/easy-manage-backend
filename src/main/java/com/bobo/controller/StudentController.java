package com.bobo.controller;

import com.bobo.mapper.StudentMapper;
import com.bobo.pojo.Result;
import com.bobo.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author boboking
 * @date 2023/6/2
 * @description
 */
//@Slf4j
//@RestController
//public class StudentController {
//
//    @Autowired
//    private StudentMapper studentMapper;
//
//    @GetMapping("/student")
//    public Result student() {
//
//        List<Student> students = studentMapper.select();
//        return Result.success(students);
//    }
//}
