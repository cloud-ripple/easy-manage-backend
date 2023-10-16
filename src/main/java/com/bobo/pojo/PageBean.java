package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author boboking
 * @date 2023/5/31
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

    private Long total; //总记录数 select count(*) from tb_emp;
    // 起始索引 = 每页展示记录数 * (当前页码 - 1)
    private List<T> rows; //当前页数据列表  select * from tb_emp limit 起始索引, 每页展示记录数;
}
