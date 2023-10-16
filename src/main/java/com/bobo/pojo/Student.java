package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author boboking
 * @date 2023/6/2
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String grade;
    private String gender;
    private String classes;
    private String studentId;
    private double math;
    private double python;
    private double compile;

}
