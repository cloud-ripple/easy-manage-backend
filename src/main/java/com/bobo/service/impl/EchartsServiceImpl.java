package com.bobo.service.impl;

import com.bobo.mapper.EchartsMapper;
import com.bobo.pojo.BarChart;
import com.bobo.pojo.PieChart;
import com.bobo.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boboking
 * @date 2023/7/1
 * @description
 */

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsMapper echartsMapper;

    // 员工统计柱状图 1
    @Override
    public BarChart getEmpChart(String info) {
        String[] deptData = echartsMapper.getDeptData(); //部门信息
        Integer[] manData = echartsMapper.countManData(); //每个部门男性员工数量
        Integer[] womanData = echartsMapper.countWomanData(); //每个部门女性员工数量
        // 数据封装
        BarChart e = new BarChart();
        e.setInfo(info);
        e.setDeptData(deptData);
        e.setManData(manData);
        e.setWomanData(womanData);

        return e;
    }


    // 部门统计饼图 1
    @Override
    public List<PieChart> getPieChart() {
        List<PieChart> pieCharts =  echartsMapper.countDept();

        return pieCharts;
    }

    // 部门统计饼图 2
    @Override
    public List<List<PieChart>> getPieChart2() {
        List<PieChart> manAtDept = echartsMapper.countManAtDept(); //每个部门男性员工数量
        List<PieChart> womanAtDept = echartsMapper.countWomanAtDept(); //每个部门女性员工数量
        ArrayList<List<PieChart>> list = new ArrayList<>();
        list.add(manAtDept);
        list.add(womanAtDept);
        return list;
    }

    @Override
    public String[] getDeptData() {
        return echartsMapper.getDeptData();
    }
}
