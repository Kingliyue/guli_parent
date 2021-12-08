package com.atliyue.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseInfoVo implements Serializable {

    private String title;
    private String cover;
    private String price;
    private String teacherName;
    private String lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;

}
