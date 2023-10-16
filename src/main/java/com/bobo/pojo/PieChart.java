package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author boboking
 * @date 2023/7/1
 * @description 饼图
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PieChart {

    // 饼图数据
    private Integer value; //数量值
    private String name; //部门名称

}
