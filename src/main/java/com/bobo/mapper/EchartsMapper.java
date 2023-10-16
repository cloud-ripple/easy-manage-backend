package com.bobo.mapper;

import com.bobo.pojo.PieChart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author boboking
 * @date 2023/7/1
 * @description
 */

@Mapper
public interface EchartsMapper {

    /**
     * 统计所有部门信息
     * @return
     */
    @Select("select " +
                "(case id " +
                "when 1 then '人力资源部'" +
                "when 2 then '财务部'" +
                "when 3 then '销售部'" +
                "when 4 then '后勤部'" +
                "when 5 then '市场部'" +
                "when 6 then '生产部'" +
                "when 7 then '采购部'" +
                "else '非法部门' end) as '部门名'" +
            "from tb_dept;")
    String[] getDeptData();

    /**
     * 统计每个部门男性员工的数量  在 tb_emp 员工表中 gender字段 1 男，2 女
     *
     * @return
     */
    @Select("select count(*) as '各部门男性员工数量' from tb_emp where gender = 1 group by dept_id;")
    Integer[] countManData();

    /**
     * 统计每个部门女性员工的数量 在 tb_emp 员工表中 gender字段 1 男，2 女
     *
     * @return
     */
    @Select("select count(*) as '各部门女性员工数量' from tb_emp where gender = 2 group by dept_id;")
    Integer[] countWomanData();


    /**
     * 统计每个部门的所有员工数量
     * @return
     */
    @Select("select " +
                "(case dept_id " +
                "when 1 then '人力资源部'" +
                "when 2 then '财务部'" +
                "when 3 then '销售部'" +
                "when 4 then '后勤部'" +
                "when 5 then '市场部'" +
                "when 6 then '生产部'" +
                "when 7 then '采购部'" +
                "else '非法部门' end) as 'name'," +
                "count(*) as 'value' " +
            "from tb_emp" +
            " group by dept_id")
    List<PieChart> countDept();  /** 这里的查询结果集 起别名的字段必须要与封装对象的属性名相同一致，否则结果封装不进去，导致对象中的属性为 null */


    /**
     * 统计每个部门男性员工数量
     * @return
     */
    @Select("select" +
            "   (case dept_id" +
            "    when 1 then '人力资源部'" +
            "    when 2 then '财务部'" +
            "    when 3 then '销售部'" +
            "    when 4 then '后勤部'" +
            "    when 5 then '市场部'" +
            "    when 6 then '生产部'" +
            "    when 7 then '采购部'" +
            "    else '非法部门' end)  as 'name'," +
            "   count(*) as 'value'" +
            "from tb_emp " +
            "where gender = 1 " +
            "group by dept_id;")
    List<PieChart> countManAtDept();


    /**
     * 统计每个部门女性员工数量
     * @return
     */
    @Select("select" +
            "   (case dept_id" +
            "    when 1 then '人力资源部'" +
            "    when 2 then '财务部'" +
            "    when 3 then '销售部'" +
            "    when 4 then '后勤部'" +
            "    when 5 then '市场部'" +
            "    when 6 then '生产部'" +
            "    when 7 then '采购部'" +
            "    else '非法部门' end)  as 'name'," +
            "   count(*) as 'value'" +
            "from tb_emp " +
            "where gender = 2 " +
            "group by dept_id;")
    List<PieChart> countWomanAtDept();
}
