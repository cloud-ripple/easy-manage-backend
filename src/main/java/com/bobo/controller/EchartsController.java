package com.bobo.controller;

import com.bobo.pojo.BarChart;
import com.bobo.pojo.PieChart;
import com.bobo.pojo.Result;
import com.bobo.service.EchartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boboking
 * @date 2023/7/1
 * @description 图标统计管理
 */

@Slf4j
@RestController
@RequestMapping
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    @GetMapping("/barChart")
    public Result barChart(String info) {
        log.info("echarts图表统计：{}", info);
        BarChart barChart = echartsService.getEmpChart(info);

        return Result.success(barChart);
    }

    @GetMapping("/pieChart")
    public Result pieChart(String info) {
        log.info("echarts图表统计：{}", info);
        List<PieChart> pieCharts = echartsService.getPieChart();

        return Result.success(pieCharts);
    }

    @PostMapping("/pieChart2")
    public Result pieChart2(String info) {
        log.info("echarts图表统计：{}", info);
        List<List<PieChart>> pieChart2 = echartsService.getPieChart2();

        ArrayList<Object> list = new ArrayList<>();
        list.add(echartsService.getDeptData());
        list.add(pieChart2);

        return Result.success(list);
    }

}
