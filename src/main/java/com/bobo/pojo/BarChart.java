package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author boboking
 * @date 2023/7/1
 * @description 柱状图
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarChart {

    // 柱状图相关数据
    private String info; //图表信息
    private String[] deptData; //部门数据
    private Integer[] manData; //男性数据
    private Integer[] womanData; //女性数据

}
