package com.atliyue.edu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 根据文件对应的
 */
@Data
public class SubjectData {
    //一级分类
    @ExcelProperty(value = "一级分类",index = 0)
    private String oneSubjectName;
    //二级分类
    @ExcelProperty(value = "二级分类",index = 1)
    private String twoSubjectName;
}
