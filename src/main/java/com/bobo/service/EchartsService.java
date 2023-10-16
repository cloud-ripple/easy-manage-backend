package com.bobo.service;

import com.bobo.pojo.BarChart;
import com.bobo.pojo.PieChart;

import java.util.List;

/**
 * @author boboking
 * @date 2023/7/1
 * @description 图表
 */
public interface EchartsService {

    /**
     * 根据指定的参数信息，返回员工图表相关数据
     * @param info 前端传递过来的参数
     * @return 第一种柱状图 各部门男性和女性员工分布统计
     */
    BarChart getEmpChart(String info);


    /**
     * 根据指定的参数信息，返回部门表相关数据
     * @return 第一种饼图 各部门员工数量统计
     */
    List<PieChart> getPieChart();


    /**
     * @return 第二种饼图 各部门男性员工数量统计、女性员工数量统计
     */
    List<List<PieChart>> getPieChart2();


    /**
     * 统计所有部门名称
     * @return 每个部门名
     */
    String[] getDeptData();


}
